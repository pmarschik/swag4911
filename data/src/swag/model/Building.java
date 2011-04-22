package swag.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Building {

	@Embedded
	private BuildingLevel isOfLevel;

	public void setIsOfLevel(BuildingLevel isOfLevel) {
		this.isOfLevel = isOfLevel;
	}

	public BuildingLevel getIsOfLevel() {
		return isOfLevel;
	}
}
