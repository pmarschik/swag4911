package swag49.web.model;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "messagequeryresponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageQueryResponse {
    @XmlElement(name = "query")
    private MessageQueryDTO messageQuery;

    @XmlElementWrapper(name = "messages")
    @XmlElement(name = "message")
    private List<MessageDTO> messages;

    public MessageQueryResponse() {}

    public MessageQueryResponse(MessageQueryDTO messageQuery, List<MessageDTO> messages) {
        this.messageQuery = messageQuery;
        this.messages = messages;
    }

    public MessageQueryDTO getMessageQuery() {
        return messageQuery;
    }

    public void setMessageQuery(MessageQueryDTO messageQuery) {
        this.messageQuery = messageQuery;
    }

    public List<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDTO> messages) {
        this.messages = messages;
    }
}
