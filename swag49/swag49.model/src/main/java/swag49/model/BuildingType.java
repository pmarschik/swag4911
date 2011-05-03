package swag49.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BuildingType {
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<BuildingLevel> levels = new HashSet<BuildingLevel>();

	public Long getId() {
		return id;
	}

	public Set<BuildingLevel> getLevels() {
		return levels;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLevels(Set<BuildingLevel> levels) {
		this.levels = levels;
	}

	public void setName(String name) {
		this.name = name;
	}

}
