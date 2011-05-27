package swag49.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Base {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Boolean home;

	@ManyToOne(optional = false)
	private Player owner;

	@OneToOne(optional = false)
	private Tile locatedOn;

	@OneToMany(mappedBy = "base")
	private Set<Square> consistsOf = new HashSet<Square>();

	@AttributeOverrides({
			@AttributeOverride(name = "amount_gold", column = @Column(name = "production_gold")),
			@AttributeOverride(name = "amount_stone", column = @Column(name = "production_stone")),
			@AttributeOverride(name = "amount_wood", column = @Column(name = "production_wood")),
			@AttributeOverride(name = "amount_crops", column = @Column(name = "production_crops")) })
	private ResourceValue resourceProduction = new ResourceValue();

	public ResourceValue getResourceProduction() {
		return resourceProduction;
	}

	public void setResourceProduction(ResourceValue resourceProduction) {
		this.resourceProduction = resourceProduction;
	}

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
