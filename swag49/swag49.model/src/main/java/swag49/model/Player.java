package swag49.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

	@Embedded
	private ResourceValue resources = new ResourceValue();

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

	public void getOwns(Set<Base> bases) {
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

	public Set<Base> setOwns() {
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
