package swag49.messaging;

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
import swag49.model.User;
import swag49.util.Log;

import java.util.Date;

@Component("webMessageHandler")
public class WebMessageHandler implements MessageReceiver {
    @Log
    private Logger logger;

    @Autowired
    @Qualifier("messageDAO")
    private DataAccessObject<Message> messageDAO;

    @Autowired
    @Qualifier("userDAO")
    private DataAccessObject<User> userDAO;

    @Autowired
    private RestTemplate restTemplate;

    @ServiceActivator
    @Transactional
    public Message handleMessage(Message message) {
        logger.info("Sending message {} to web-server", message);
        message.setReceiveDate(new Date());
        messageDAO.update(message);

        String senderUsername = userDAO.get(message.getSenderUserId()).getUsername();
        String receiverUsername = userDAO.get(message.getReceiverUserId()).getUsername();

        MessageDTO messageDTO =
                new MessageDTO(message.getSubject(), message.getContent(), message.getSenderUserId(), senderUsername,
                        message.getReceiverUserId(), receiverUsername, message.getSendDate());

        String requestUri = message.getMapUrl() + "/swag-api/messaging/receive";
        restTemplate.put(requestUri, messageDTO);

        return message;
    }
}
