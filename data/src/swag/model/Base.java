package swag.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean isHome() {
		return home;
	}

	public void setHome(Boolean home) {
		this.home = home;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public Player getOwner() {
		return owner;
	}

	public void setLocatedOn(Tile locatedOn) {
		this.locatedOn = locatedOn;
	}

	public Tile getLocatedOn() {
		return locatedOn;
	}
}
