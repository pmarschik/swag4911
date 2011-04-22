package swag.model;

@javax.persistence.Entity
public class User {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;

	private String name;

	@javax.persistence.Transient
	protected User related;

	@javax.persistence.Transient
	public java.util.Set<User> friends;

	@javax.persistence.ElementCollection
	private java.util.Set<String> usernames;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public User getRelated() {
		return related;
	}

	public java.util.Set<User> getFriends() {
		return friends;
	}

	public java.util.Set<String> getUsernames() {
		return usernames;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRelated(User related) {
		this.related = related;
	}

	public void setFriends(java.util.Set<User> friends) {
		this.friends = friends;
	}

	public void setUsernames(java.util.Set<String> usernames) {
		this.usernames = usernames;
	}

}
