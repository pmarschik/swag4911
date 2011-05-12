package swag49.messaging;

import org.springframework.integration.message.GenericMessage;
import swag49.model.Message;

import java.util.Map;

public class SwagMessage extends GenericMessage<Message> {
    public SwagMessage(Message payload) {
        super(payload);
    }

    public SwagMessage(Message payload, Map<String, Object> headers) {
        super(payload, headers);
    }
}
