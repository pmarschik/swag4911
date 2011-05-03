package swag49.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Map {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private Integer maxUsers;
	
	@OneToMany(mappedBy="map")
	private Set<Tile> consistsOf = new HashSet<Tile>();

	public Set<Tile> getConsistsOf() {
		return consistsOf;
	}

	public Long getId() {
		return id;
	}

	public Integer getMaxUsers() {
		return maxUsers;
	}

	public void setConsistsOf(Set<Tile> tiles) {
		this.consistsOf = tiles;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMaxUsers(Integer maxUsers) {
		this.maxUsers = maxUsers;
	}
}
