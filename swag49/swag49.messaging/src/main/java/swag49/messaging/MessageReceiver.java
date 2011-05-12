package swag49.messaging;

import swag49.model.Message;

public interface MessageReceiver {

    /**
     * Handles a message.
     *
     * @param message The message to handle.
     * @return {@code true} if the message has been handled, {@code false} otherwise.
     */
    public Message handleMessage(Message message);

}
