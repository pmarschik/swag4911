package swag49.messaging;

import swag49.messaging.model.Message;

public interface MessageReceiver {

    public Message handleMessage(Message message);

}
