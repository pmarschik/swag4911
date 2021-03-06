package swag49.model;

import com.google.common.collect.Sets;

import javax.persistence.*;
import java.util.Set;

// need to rename because postgresql does not like tables named user ...
@Entity(name="SWAG_User")
public class User {

    @Id
	@Column(nullable = false, length = 50, unique = true)
	private String username;

	@Column(nullable = false, length = 50)
	private String password;

	@Column(length = 255)
	private String firstName;

	@Column(length = 255)
	private String lastName;

	@Column(length = 100)
	private String email;

	@Column(nullable = false)
	private Integer utcOffset;

	@ManyToMany
	private Set<MapLocation> mapLocations = Sets.newHashSet();

	@Embedded
	private Address address;

	public User() {
	}

	public Address getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPassword() {
		return password;
	}

	public Set<MapLocation> getMapLocations() {
		return mapLocations;
	}

	public String getUsername() {
		return username;
	}

	public Integer getUtcOffset() {
		return utcOffset;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMapLocations(Set<MapLocation> mapLocations) {
		this.mapLocations = mapLocations;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUtcOffset(Integer utcOffset) {
		this.utcOffset = utcOffset;
	}
}
