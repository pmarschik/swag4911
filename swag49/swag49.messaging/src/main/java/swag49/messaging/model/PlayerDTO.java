package swag49.messaging.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerDTO {
    @XmlAttribute(name = "id")
    private long id;
    @XmlElement(name = "userid")
    private long userId;
    @XmlElement(name = "online")
    private boolean isOnline;

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, Long userId, boolean online) {
        this.id = id;
        this.userId = userId;
        isOnline = online;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
