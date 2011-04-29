package swag.model;

@javax.persistence.Entity
public class Player {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;

	@javax.persistence.Column(nullable = false)
	private Boolean online;

	@javax.persistence.Column(nullable = false)
	private Boolean deleted;

	@javax.persistence.ManyToOne(optional = false)
	private User user;

	@javax.persistence.OneToOne(optional = false)
	private Map plays;

	@javax.persistence.OneToMany(mappedBy = "owner")
	private java.util.Set<Base> owns = new java.util.HashSet<Base>();

	@javax.persistence.OneToMany(mappedBy = "player")
	private java.util.Set<Resource> resources = new java.util.HashSet<Resource>();

	@javax.persistence.OneToMany(mappedBy = "player")
	private java.util.Set<Action> actions = new java.util.HashSet<Action>();

	public Player() {
	}

	public Long getId() {
		return id;
	}

	public Boolean getOnline() {
		return online;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public User getUser() {
		return user;
	}

	public Map getPlays() {
		return plays;
	}

	public java.util.Set<Base> getOwns() {
		return owns;
	}

	public java.util.Set<Resource> getResources() {
		return resources;
	}

	public java.util.Set<Action> getActions() {
		return actions;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setPlays(Map plays) {
		this.plays = plays;
	}

	public void setOwns(java.util.Set<Base> owns) {
		this.owns = owns;
	}

	public void setResources(java.util.Set<Resource> resources) {
		this.resources = resources;
	}

	public void setActions(java.util.Set<Action> actions) {
		this.actions = actions;
	}

}
