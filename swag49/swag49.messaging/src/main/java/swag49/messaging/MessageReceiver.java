package swag49.messaging;

import swag49.model.Message;

public interface MessageReceiver {

    public Message handleMessage(Message message);

}
