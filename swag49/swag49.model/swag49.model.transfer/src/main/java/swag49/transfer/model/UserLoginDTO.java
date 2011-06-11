package swag49.transfer.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author michael
 */
public class UserLoginDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String username;
    @NotNull
    @Size(min = 1, max = 50)
    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
