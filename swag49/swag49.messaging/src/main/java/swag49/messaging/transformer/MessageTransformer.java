package swag49.messaging.transformer;

import com.google.common.base.Function;
import org.springframework.integration.annotation.Transformer;
import swag49.messaging.model.Message;
import swag49.transfer.model.MessageDTO;

public interface MessageTransformer extends Function<MessageDTO, Message> {
    @Transformer
    Message apply(MessageDTO input);
}
