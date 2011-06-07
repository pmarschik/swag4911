package swag49.messaging.transformer.impl;

import org.slf4j.Logger;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;
import swag49.messaging.model.Message;
import swag49.messaging.model.MessageDTO;
import swag49.messaging.transformer.MessageTransformer;
import swag49.util.Log;

@Component("messageTransformer")
public class MessageTransformerImpl implements MessageTransformer {
    @Log
    private Logger log;

    @Override
    @Transformer
    public Message apply(MessageDTO input) {
        Message output = new Message();

        output.setContent(input.getContent());
        output.setSendDate(input.getSent());
        output.setReceiveDate(input.getReceived());

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

        return  output;
    }
}
