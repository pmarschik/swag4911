package swag49.web.model;

import com.google.common.base.Objects;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageDTO {
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class UserDTO {
        @XmlElement(name = "username")
        @Nullable
        private String username;
        @XmlAttribute(name = "id")
        private Long id;

        public UserDTO() {}

        public UserDTO(@Nullable String username, Long id) {
            this.username = username;
            this.id = id;
        }

        @Nullable
        public String getUsername() { return username; }

        public void setUsername(@Nullable String username) { this.username = username; }

        public Long getId() { return id; }

        public void setId(Long id) { this.id = id; }

        @Override
        public int hashCode() {
            return Objects.hashCode(username, id);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("id", id)
                    .add("username", username)
                    .toString();
        }
    }

    @XmlAttribute(name = "map")
    private String mapUrl;

    @XmlElement(name = "subject")
    private String subject;

    @XmlElement(name = "content")
    private String content;

    @XmlElement(name = "sender")
    private UserDTO sender;

    @XmlElement(name = "receiver")
    private UserDTO receiver;

    @XmlAttribute(name = "sent")
    private Date sent;

    @XmlAttribute(name = "received", required = false)
    @Nullable
    private Date received;

    public MessageDTO() { }

    public MessageDTO(String subject, String content, Long senderId, @Nullable String senderUsername, Long receiverId,
                      @Nullable String receiverUsername, Date sent, @Nullable Date received, String mapUrl) {
        this.subject = subject;
        this.content = content;
        this.sender = new UserDTO(senderUsername, senderId);
        this.receiver = new UserDTO(receiverUsername, receiverId);
        this.sent = sent;
        this.received = received;
        this.mapUrl = mapUrl;
    }

    public String getMapUrl() {
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getSender() {
        return sender;
    }

    public void setSender(UserDTO sender) {
        this.sender = sender;
    }

    public UserDTO getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDTO receiver) {
        this.receiver = receiver;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    @Nullable
    public Date getReceived() {
        return received;
    }

    public void setReceived(@Nullable Date received) {
        this.received = received;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sender, receiver, sent, received, subject, content);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("sender", sender)
                .add("receiver", receiver)
                .add("sent", sent)
                .add("received", received)
                .add("subject", subject)
                .add("content", content)
                .toString();
    }
}
