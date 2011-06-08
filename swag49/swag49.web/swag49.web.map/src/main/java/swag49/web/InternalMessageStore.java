package swag49.web;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import swag49.web.model.MessageDTO;

import java.util.Collection;

public class InternalMessageStore {

    private Multimap<Long, MessageDTO> receivedMessages = HashMultimap.create();
    private Multimap<Long, MessageDTO> messageListCache = HashMultimap.create();

    public Collection<MessageDTO> getMessageList(Long userId) {
        return messageListCache.get(userId);
    }

    public Collection<MessageDTO> getNewMessagesAndRemoveFromCache(Long userId) {
        Collection<MessageDTO> result = receivedMessages.get(userId);
        receivedMessages.removeAll(userId);
        return result;
    }

    public void addMessageList(Long userId, Iterable<MessageDTO> messages) {
        messageListCache.putAll(userId, messages);
    }

    public void addReceivedMessage(Long userId, MessageDTO message) {
        receivedMessages.put(userId, message);
    }
}
