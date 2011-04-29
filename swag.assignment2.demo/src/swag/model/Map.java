package swag.model;

@javax.persistence.Entity
public class Map {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;

	@javax.persistence.Column(nullable = false)
	private Integer maxUsers;

	@javax.persistence.OneToMany(mappedBy = "map")
	private java.util.Set<Tile> consistsOf = new java.util.HashSet<Tile>();

	public Map() {
	}

	public Long getId() {
		return id;
	}

	public Integer getMaxUsers() {
		return maxUsers;
	}

	public java.util.Set<Tile> getConsistsOf() {
		return consistsOf;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMaxUsers(Integer maxUsers) {
		this.maxUsers = maxUsers;
	}

	public void setConsistsOf(java.util.Set<Tile> consistsOf) {
		this.consistsOf = consistsOf;
	}

}
