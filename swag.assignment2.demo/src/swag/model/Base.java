package swag.model;

@javax.persistence.Entity
public class Base {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;

	@javax.persistence.Column(nullable = false)
	private Boolean home;

	@javax.persistence.ManyToOne(optional = false)
	private Player owner;

	@javax.persistence.ManyToOne(optional = false)
	private Tile locatedOn;

	@javax.persistence.OneToMany(mappedBy = "base")
	private java.util.Set<Square> consistsOf = new java.util.HashSet<Square>();

	public Base() {
	}

	public Long getId() {
		return id;
	}

	public Boolean getHome() {
		return home;
	}

	public Player getOwner() {
		return owner;
	}

	public Tile getLocatedOn() {
		return locatedOn;
	}

	public java.util.Set<Square> getConsistsOf() {
		return consistsOf;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setHome(Boolean home) {
		this.home = home;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public void setLocatedOn(Tile locatedOn) {
		this.locatedOn = locatedOn;
	}

	public void setConsistsOf(java.util.Set<Square> consistsOf) {
		this.consistsOf = consistsOf;
	}

}
