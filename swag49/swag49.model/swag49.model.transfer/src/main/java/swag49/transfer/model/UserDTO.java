package swag49.transfer.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author michael
 */
public class UserDTO {

    @NotNull
    @Size(min = 1, max = 50)
    private String username;
    @NotNull
    @Size(min = 1, max = 50)
    private String password;
    @Size(min = 1, max = 255)
    private String firstName;
    @Size(min = 1, max = 255)
    private String lastName;
    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)")
    private String email;
    private int utcOffset;
    private String state;
    private String city;
    private String postalCode;
    private String street;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String firstName, String lastName, String email, int utcOffset,
                   String addressState, String addressCity, String addressPostalCode, String addressStreet) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.utcOffset = utcOffset;
        this.state = addressState;
        this.city = addressCity;
        this.postalCode = addressPostalCode;
        this.street = addressStreet;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(int utcOffset) {
        this.utcOffset = utcOffset;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", utcOffset=" + utcOffset +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
