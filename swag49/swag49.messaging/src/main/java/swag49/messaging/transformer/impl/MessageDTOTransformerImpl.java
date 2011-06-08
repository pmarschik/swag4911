package swag49.messaging.transformer.impl;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;
import swag49.dao.DataAccessObject;
import swag49.messaging.model.Message;
import swag49.messaging.model.MessageDTO;
import swag49.messaging.transformer.MessageDTOTransformer;
import swag49.model.User;

@Component("messageDTOTransformer")
public class MessageDTOTransformerImpl implements MessageDTOTransformer {
    @Autowired
    @Qualifier("userDAO")
    private DataAccessObject<User, Long> userDAO;

    @Override
    @Transformer
    public MessageDTO apply(Message input) {
        User receiver = Preconditions.checkNotNull(userDAO.get(input.getReceiverUserId()), "receiver not existing");
        User sender = Preconditions.checkNotNull(userDAO.get(input.getSenderUserId()), "sender not existing");

        return new MessageDTO(input.getSubject(), input.getContent(), input.getSenderUserId(), sender.getUsername(),
                input.getReceiverUserId(), receiver.getUsername(), input.getSendDate(), input.getReceiveDate(),
                input.getMapUrl());
    }
}
