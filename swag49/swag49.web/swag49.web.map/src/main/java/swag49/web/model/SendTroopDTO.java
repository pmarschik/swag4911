package swag49.web.model;

/**
 * Created by IntelliJ IDEA.
 * User: Ben
 * Date: 6/11/11
 * Time: 3:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class SendTroopDTO
{
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getSendMe() {
        return sendMe;
    }

    public void setSendMe(boolean sendMe) {
        this.sendMe = sendMe;
    }

    String name;
    boolean sendMe;
}
