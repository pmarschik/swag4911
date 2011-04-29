package swag49.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Building {

	@ManyToOne(optional = false)
	private BuildingType type;
	
	@ManyToOne(optional = false)
	private BuildingLevel isOfLevel;

	public void setType(BuildingType type) {
		this.type = type;
	}

	public BuildingType getType() {
		return type;
	}
	
	public void setIsOfLevel(BuildingLevel isOfLevel) {
		this.isOfLevel = isOfLevel;
	}

	public BuildingLevel getIsOfLevel() {
		return isOfLevel;
	}
}
