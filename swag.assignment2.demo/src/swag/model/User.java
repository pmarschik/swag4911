package swag.model;

@javax.persistence.Entity
@javax.persistence.Table(name = "swag_user")
public class User {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;

	@javax.persistence.Column(nullable = false, unique = true, length = 50)
	@org.hibernate.annotations.Index(name = "idx_username")
	private String username;

	@javax.persistence.Column(nullable = false, length = 50)
	private String password;

	@javax.persistence.Column(length = 255)
	private String firstName;

	@javax.persistence.Column(length = 255)
	private String lastName;

	@javax.persistence.Column(length = 100)
	private String email;

	@javax.persistence.Column(nullable = false)
	private Integer utcOffset;

	@javax.persistence.OneToMany(mappedBy = "user")
	private java.util.Set<Player> players = new java.util.HashSet<Player>();

	@javax.persistence.Embedded
	private Address address;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public Integer getUtcOffset() {
		return utcOffset;
	}

	public java.util.Set<Player> getPlayers() {
		return players;
	}

	public Address getAddress() {
		return address;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUtcOffset(Integer utcOffset) {
		this.utcOffset = utcOffset;
	}

	public void setPlayers(java.util.Set<Player> players) {
		this.players = players;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
