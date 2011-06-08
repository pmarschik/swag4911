package swag49.messaging.handler;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import swag49.dao.DataAccessObject;
import swag49.messaging.model.Message;
import swag49.messaging.model.MessageDTO;
import swag49.messaging.model.MessageQueryDTO;
import swag49.messaging.model.MessageQueryResponse;
import swag49.messaging.transformer.MessageDTOTransformer;
import swag49.util.Log;

import java.util.List;

@Component("messageListHandler")
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

    @ServiceActivator
    @Transactional("swag49.messaging")
    public void handleMessage(MessageQueryDTO messageQuery) {
        Message messageExample = new Message();
        messageExample.setReceiverUserId(messageQuery.getUserId());
        messageExample.setMapUrl(messageQuery.getMapURL());

        List<Message> receiverMessages = messageDAO.queryByExample(messageExample);
        logger.info("got {} messages with receiver={}", receiverMessages.size(), messageQuery.getUserId());

        //noinspection NullableProblems
        messageExample.setReceiverUserId(null);
        messageExample.setSenderUserId(messageQuery.getUserId());

        List<Message> senderMessages = messageDAO.queryByExample(messageExample);
        logger.info("got {} messages with sender={}", senderMessages.size(), messageQuery.getUserId());

        List<Message> messages = Lists.newArrayList(senderMessages);
        messages.addAll(receiverMessages);
        messages.addAll(messageDAO.queryByExample(messageExample));

        List<MessageDTO> messageDTOs = Lists.transform(messages, messageDTOTransformer);

        String requestUri = messageQuery.getMapURL() + "/swag-api/messaging/list";
        restTemplate.put(requestUri, new MessageQueryResponse(messageQuery, messageDTOs));
    }
}
