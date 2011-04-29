package swag.model;

@javax.persistence.Embeddable
public class Building {

	@javax.persistence.Embedded
	private BuildingLevel isOfLevel;

	@javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
	@javax.persistence.Column(nullable = false)
	private BuildingType type;

	public Building() {
	}

	public BuildingLevel getIsOfLevel() {
		return isOfLevel;
	}

	public BuildingType getType() {
		return type;
	}

	public void setIsOfLevel(BuildingLevel isOfLevel) {
		this.isOfLevel = isOfLevel;
	}

	public void setType(BuildingType type) {
		this.type = type;
	}

}
