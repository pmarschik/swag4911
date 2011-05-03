package swag49.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Base {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Boolean home;

	@ManyToOne(optional = false)
	private Player owner;

	@ManyToOne(optional = false)
	private Tile locatedOn;

	@OneToMany(mappedBy = "base")
	private Set<Square> consistsOf = new HashSet<Square>();

	public Set<Square> getConsistsOf() {
		return consistsOf;
	}

	public Long getId() {
		return id;
	}

	public Tile getLocatedOn() {
		return locatedOn;
	}

	public Player getOwner() {
		return owner;
	}

	public Boolean isHome() {
		return home;
	}

	public void setConsistsOf(Set<Square> consistsOf) {
		this.consistsOf = consistsOf;
	}

	public void setHome(Boolean home) {
		this.home = home;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLocatedOn(Tile locatedOn) {
		this.locatedOn = locatedOn;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
}
