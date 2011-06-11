package swag49.transfer.model;

import com.google.common.base.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "messagequery")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageQueryDTO {

    @XmlElement(name = "mapurl")
    private String mapURL;

    @XmlElement(name = "user")
    private String userId;

    public MessageQueryDTO() {}

    public MessageQueryDTO(String mapURL, String userId) {
        this.mapURL = mapURL;
        this.userId = userId;
    }

    public String getMapURL() {
        return mapURL;
    }

    public void setMapURL(String mapURL) {
        this.mapURL = mapURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageQueryDTO that = (MessageQueryDTO) o;

        return Objects.equal(mapURL, that.mapURL) && Objects.equal(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mapURL, userId);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mapURL", mapURL)
                .add("userId", userId)
                .toString();
    }
}
