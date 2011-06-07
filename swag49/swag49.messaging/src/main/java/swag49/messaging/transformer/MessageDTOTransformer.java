package swag49.messaging.transformer;

import com.google.common.base.Function;
import org.springframework.integration.annotation.Transformer;
import swag49.messaging.model.Message;
import swag49.messaging.model.MessageDTO;

public interface MessageDTOTransformer extends Function<Message, MessageDTO> {
    @Transformer
    MessageDTO apply(Message input);
}
