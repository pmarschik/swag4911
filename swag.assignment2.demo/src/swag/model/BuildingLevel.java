package swag.model;

@javax.persistence.Embeddable
public class BuildingLevel {

	@javax.persistence.Column(nullable = false)
	private Integer level;

	@javax.persistence.Column(nullable = false)
	private Float factor_per_time;

	@javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
	@javax.persistence.Column(nullable = false)
	private ResourceType cost;

	public BuildingLevel() {
	}

	public Integer getLevel() {
		return level;
	}

	public Float getFactor_per_time() {
		return factor_per_time;
	}

	public ResourceType getCost() {
		return cost;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setFactor_per_time(Float factor_per_time) {
		this.factor_per_time = factor_per_time;
	}

	public void setCost(ResourceType cost) {
		this.cost = cost;
	}

}
