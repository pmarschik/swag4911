package swag49.messaging.router;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import swag49.messaging.model.Message;
import swag49.messaging.model.PlayerDTO;
import swag49.util.Log;

@Component("messageRouter")
public class MessageRouter {
    @Log
    private Logger logger;
    @Autowired
    @Qualifier("emailMessageChannel")
    private MessageChannel emailChannel;
    @Autowired
    @Qualifier("webMessageChannel")
    private MessageChannel webChannel;
    @Autowired
    private RestTemplate restTemplate;

    @Router
    @Transactional
    public MessageChannel route(Message message) {
        String requestUri = message.getMapUrl() + "swag-api/messaging/user/{userId}";

        PlayerDTO receiverPlayer = restTemplate.getForObject(requestUri, PlayerDTO.class, message.getReceiverUserId());

        if (receiverPlayer == null) {
            logger.warn("User with id {} wasn't found on map {}!", message.getReceiverUserId(), message.getMapUrl());
            return null;
        }

        logger.debug("player {} was {}", receiverPlayer.getId(), receiverPlayer.isOnline() ? "online" : "offline");

        if (receiverPlayer.isOnline())
            return webChannel;
        else
            return emailChannel;
    }

}
