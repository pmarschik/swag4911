package swag49.messaging.handler;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import swag49.dao.DataAccessObject;
import swag49.messaging.model.Message;
import swag49.messaging.transformer.MessageDTOTransformer;
import swag49.transfer.model.MessageDTO;
import swag49.transfer.model.MessageQueryDTO;
import swag49.transfer.model.MessageQueryResponse;
import swag49.util.Log;

import java.util.List;
import java.util.Set;

@Controller("messageListHandler")
public class MessageListHandler {
    @Log
    private Logger logger;

    @Autowired
    @Qualifier("messageDAO")
    private DataAccessObject<Message, Long> messageDAO;

    @Autowired
    @Qualifier("messageDTOTransformer")
    private MessageDTOTransformer messageDTOTransformer;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional("swag49.messaging")
    private List<Message> getAllMessagesByUser(String userId, String mapUrl) {
        Message messageExample = new Message();
        messageExample.setReceiverUserId(userId);
        messageExample.setMapUrl(mapUrl);

        List<Message> receiverMessages = messageDAO.queryByExample(messageExample);
        logger.info("got {} messages with receiver={}", receiverMessages.size(), userId);

        //noinspection NullableProblems
        messageExample.setReceiverUserId(null);
        messageExample.setSenderUserId(userId);

        List<Message> senderMessages = messageDAO.queryByExample(messageExample);
        logger.info("got {} messages with sender={}", senderMessages.size(), userId);

        List<Message> messages = Lists.newArrayList(senderMessages);
        messages.addAll(receiverMessages);
        messages.addAll(messageDAO.queryByExample(messageExample));

        return messages;
    }

    @RequestMapping(value="/get", method = {RequestMethod.PUT, RequestMethod.POST})
    @ServiceActivator
    @ResponseBody
    public MessageQueryResponse handleMessage(@RequestBody MessageQueryDTO messageQuery) {
        List<Message> allMessages = getAllMessagesByUser(messageQuery.getUserId(), messageQuery.getMapURL());
        List<MessageDTO> allMessageDTOs = Lists.transform(allMessages, messageDTOTransformer);
        Set<MessageDTO> messageDTOs = Sets.newHashSet();

        for (MessageDTO messageDTO : allMessageDTOs)
            messageDTOs.add(messageDTO);

        String requestUri = messageQuery.getMapURL() + "/swag-api/messaging/list";
        MessageQueryResponse messageQueryResponse = new MessageQueryResponse(messageQuery, messageDTOs);
        //restTemplate.put(requestUri, messageQueryResponse);

        return messageQueryResponse;
    }
}
