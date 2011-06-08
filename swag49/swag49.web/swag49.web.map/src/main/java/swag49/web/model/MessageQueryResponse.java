package swag49.web.model;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "messagequeryresponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageQueryResponse {
    @XmlElement(name = "query")
    private MessageQueryDTO messageQuery;

    @XmlElementWrapper(name = "messages")
    @XmlElement(name = "message")
    private Set<MessageDTO> messages;

    public MessageQueryResponse() {}

    public MessageQueryResponse(MessageQueryDTO messageQuery, Set<MessageDTO> messages) {
        this.messageQuery = messageQuery;
        this.messages = messages;
    }

    public MessageQueryDTO getMessageQuery() {
        return messageQuery;
    }

    public void setMessageQuery(MessageQueryDTO messageQuery) {
        this.messageQuery = messageQuery;
    }

    public Set<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageDTO> messages) {
        this.messages = messages;
    }
}
