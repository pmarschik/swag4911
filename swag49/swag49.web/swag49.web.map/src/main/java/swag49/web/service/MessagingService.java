package swag49.web.service;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import swag49.dao.DataAccessObject;
import swag49.model.Player;
import swag49.util.Log;
import swag49.web.model.MessageDTO;
import swag49.web.model.MessageQueryResponse;
import swag49.web.model.PlayerDTO;

import java.util.Collection;

@Controller
public class MessagingService {

    @Log
    private Logger log;

    @Autowired
    @Qualifier("playerDAO")
    private DataAccessObject<Player> playerDAO;

    private Multimap<Long, MessageDTO> receivedMessages = HashMultimap.create();
    private Multimap<Long, MessageDTO> messageListCache = HashMultimap.create();

    @RequestMapping(value = "/messaging/user/{userId}", method = RequestMethod.GET)
    @ResponseBody
    @Transactional
    public PlayerDTO getUser(@PathVariable("userId") long userId) {
        Player playerExample = new Player();
        playerExample.setUserId(userId);

        Collection<Player> queryResult = playerDAO.queryByExample(playerExample);
        assert queryResult.size() == 1;

        Player player = queryResult.iterator().next();

        return new PlayerDTO(player.getId(), userId, player.getOnline());
    }

    @RequestMapping(value = "/messaging/receive", method = RequestMethod.PUT)
    public void receiveMessage(@RequestParam("message") MessageDTO message) {
        log.info("received message {}", message);

        receivedMessages.put(message.getReceiver().getId(), message);
    }

    @RequestMapping(value="/messaging/list", method = RequestMethod.PUT)
    public void listMessages(@RequestBody MessageQueryResponse messageQueryResponse) {
        log.info("received {} messages", messageQueryResponse.getMessages().size());

        messageListCache.putAll(messageQueryResponse.getMessageQuery().getUserId(),
                messageQueryResponse.getMessages());
    }

    public Collection<MessageDTO> getMessageList(Long userId) {
        return messageListCache.get(userId);
    }

    public Collection<MessageDTO> getNewMessagesAndRemoveFromCache(Long userId) {
        Collection<MessageDTO> result = receivedMessages.get(userId);
        receivedMessages.removeAll(userId);
        return result;
    }
}
