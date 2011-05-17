package swag49.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Player {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Boolean online;

	@Column(nullable = false)
	private Boolean deleted;

	@ManyToOne(optional = false)
	private User user;

	@ManyToOne(optional = false)
	private Map plays;

	@OneToMany(mappedBy = "owner")
	private Set<Base> owns = new HashSet<Base>();
	
	@OneToMany(mappedBy = "owner")
	private Set<Troop> commands = new HashSet<Troop>();

	public Set<Troop> getCommands() {
		return commands;
	}

	public void setCommands(Set<Troop> commands) {
		this.commands = commands;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount_gold", column = @Column(name = "resources_gold")),
			@AttributeOverride(name = "amount_stone", column = @Column(name = "resources_stone")),
			@AttributeOverride(name = "amount_wood", column = @Column(name = "resources_wood")),
			@AttributeOverride(name = "amount_crops", column = @Column(name = "resources_crops")) })
	private ResourceValue resources = new ResourceValue();

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount_gold", column = @Column(name = "income_gold")),
			@AttributeOverride(name = "amount_stone", column = @Column(name = "income_stone")),
			@AttributeOverride(name = "amount_wood", column = @Column(name = "income_wood")),
			@AttributeOverride(name = "amount_crops", column = @Column(name = "income_crops")) })
	private ResourceValue income = new ResourceValue();
	
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount_gold", column = @Column(name = "upkeep_gold")),
			@AttributeOverride(name = "amount_stone", column = @Column(name = "upkeep_stone")),
			@AttributeOverride(name = "amount_wood", column = @Column(name = "upkeep_wood")),
			@AttributeOverride(name = "amount_crops", column = @Column(name = "upkeep_crops")) })
	private ResourceValue upkeep = new ResourceValue();

	public ResourceValue getUpkeep() {
		return upkeep;
	}

	public void setUpkeep(ResourceValue upkeep) {
		this.upkeep = upkeep;
	}

	public ResourceValue getIncome() {
		return income;
	}

	public void setIncome(ResourceValue income) {
		this.income = income;
	}

	@OneToMany(mappedBy = "player")
	private Set<Action> actions = new HashSet<Action>();

	public Player() {
	}

	public Set<Action> getActions() {
		return actions;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public Long getId() {
		return id;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOwns(Set<Base> bases) {
		this.owns = bases;
	}

	public Map getPlays() {
		return plays;
	}

	public ResourceValue getResources() {
		return resources;
	}

	public User getUser() {
		return user;
	}

	public void setActions(Set<Action> actions) {
		this.actions = actions;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Set<Base> getOwns() {
		return owns;
	}

	public void setPlays(Map plays) {
		this.plays = plays;
	}

	public void setResources(ResourceValue resources) {
		this.resources = resources;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
