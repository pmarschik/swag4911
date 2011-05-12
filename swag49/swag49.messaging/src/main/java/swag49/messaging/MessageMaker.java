package swag49.messaging;

import org.hibernate.loader.custom.Return;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import swag49.model.Message;
import swag49.model.Player;

import java.util.Date;

@Component("messageMaker")
@Scope("prototype")
public class MessageMaker {
    private Message message;

    public MessageMaker() {
    }

    public MessageMaker newMessage(String subject, String content) {
        message = new Message();
        message.setSubject(subject);
        message.setContent(content);
        message.setSendDate(new Date());
        return this;
    }

    public MessageMaker newMessage(String subject) {
        return newMessage(subject, "");
    }

    public MessageMaker newMessage() {
        return newMessage("");
    }

    public MessageMaker at(Date sendDate) {
        message.setSendDate(sendDate);
        return this;
    }

    public MessageMaker from(Player sender) {
        message.setSender(sender);
        return this;
    }

    public MessageMaker to(Player receiver) {
        message.setReceiver(receiver);
        return this;
    }

    public MessageMaker withContent(String content) {
        message.setContent(content);
        return this;
    }

    public MessageMaker withSubject(String subject) {
        message.setSubject(subject);
        return this;
    }

    public MessageMaker withSubjectAndContent(String subject, String content) {
        message.setSubject(subject);
        message.setContent(content);
        return this;
    }

    public Message get() {
        return message;
    }
}
