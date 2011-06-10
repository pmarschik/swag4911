package swag49.web.model;

import javax.xml.bind.annotation.*;

@XmlRootElement(name="player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerDTO {
    @XmlAttribute(name = "id")
    private long id;
    @XmlElement(name = "userid")
    private String userId;
    @XmlElement(name = "online")
    private boolean isOnline;

    public PlayerDTO() {
    }

    public PlayerDTO(Long id, String userId, boolean online) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
