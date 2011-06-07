package swag49.messaging;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.messaging.model.Message;
import swag49.messaging.model.MessageDTO;
import swag49.util.Log;

@Component("playerMessageDTOTransformer")
public class MessageTransformer {
    @Log
    private Logger log;

    @Autowired
    @Qualifier("messageDAO")
    private DataAccessObject<Message> messageDAO;

    @Transformer
    @Transactional("swag49.messaging")
    public Message transform(MessageDTO input) {
        Message output = new Message();

        output.setContent(input.getContent());
        output.setSendDate(input.getSent());

        if (input.getReceiver() != null)
            output.setReceiverUserId(input.getReceiver().getId());
        else
            log.warn("receiver was null");

        if (input.getSender() != null)
            output.setSenderUserId(input.getSender().getId());
        else
            log.warn("sender was null");

        output.setSubject(input.getSubject());
        output.setMapUrl(input.getMapUrl());

        return messageDAO.create(output);
    }
}
