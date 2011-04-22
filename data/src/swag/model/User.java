package swag.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Index;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 50, unique = true)
	@Index(name = "username")
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
	
	@OneToMany(mappedBy = "user")
	private Set<Player> players = new HashSet<Player>();

	public User() {
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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

	public Integer getUtcOffset() {
		return utcOffset;
	}

	public void setUtcOffset(Integer utcOffset) {
		this.utcOffset = utcOffset;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Set<Player> getPlayers() {
		return players;
	}
}
