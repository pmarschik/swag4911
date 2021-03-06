package swag49.transfer.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.UUID;

@XmlRootElement(name = "credentials")
@XmlAccessorType(XmlAccessType.FIELD)
public class TokenDTO {
    @XmlElement(name="token")
    private UUID token;
    @XmlElement(name="user")
    private String userId;
    @XmlElement(name="userName")
    private String userName;

    public TokenDTO() {
    }

    public TokenDTO(UUID token, String userId, String userName) {
        this.token = token;
        this.userId = userId;
        this.userName = userName;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
