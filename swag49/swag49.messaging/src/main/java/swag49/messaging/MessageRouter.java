package swag49.messaging;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.annotation.Router;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.model.Message;
import swag49.model.Player;
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
    @Qualifier("playerDAO")
    private DataAccessObject<Player> playerDAO;

    @Router
    @Transactional
    public MessageChannel route(Message message) {
        Long receiverId = message.getReceiver().getId();
        Player receiver = playerDAO.get(receiverId);

        if(receiver == null) {
            logger.warn("Player with id {} wasn't found!", receiverId);
            return null;
        }

        logger.debug("player {} was {}", receiverId, receiver.getOnline() ? "online" : "offline");

        if (receiver.getOnline())
            return webChannel;
        else
            return emailChannel;
    }

}
