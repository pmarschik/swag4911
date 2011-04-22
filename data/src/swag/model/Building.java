package swag.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Building {

	@Embedded
	private BuildingLevel isOfLevel;

	@Enumerated(EnumType.STRING)
	private BuildingType type;

	public void setIsOfLevel(BuildingLevel isOfLevel) {
		this.isOfLevel = isOfLevel;
	}

	public BuildingLevel getIsOfLevel() {
		return isOfLevel;
	}

	public void setType(BuildingType type) {
		this.type = type;
	}

	public BuildingType getType() {
		return type;
	}
}
