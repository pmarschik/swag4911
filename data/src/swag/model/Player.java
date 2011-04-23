package swag.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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

	@OneToOne(optional = false)
	private Map plays;

	@OneToMany(mappedBy = "owner")
	private Set<Base> owns = new HashSet<Base>();
	
	@OneToMany(mappedBy = "player")
	private Set<Resource> resources = new HashSet<Resource>();

	public Player() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getOnline() {
		return online;
	}

	public void setOnline(Boolean online) {
		this.online = online;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setPlays(Map plays) {
		this.plays = plays;
	}

	public Map getPlays() {
		return plays;
	}

	public void getOwns(Set<Base> bases) {
		this.owns = bases;
	}

	public Set<Base> setOwns() {
		return owns;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<Resource> getResources() {
		return resources;
	}
}
