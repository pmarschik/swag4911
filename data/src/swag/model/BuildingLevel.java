package swag.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class BuildingLevel {

	@Column(nullable = false)
	private Integer Level;

	@Column(nullable = false)
	private Float factor_per_time;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ResourceType cost;

	public Integer getLevel() {
		return Level;
	}

	public void setLevel(Integer level) {
		Level = level;
	}

	public Float getFactor_per_time() {
		return factor_per_time;
	}

	public void setFactor_per_time(Float factorPerTime) {
		factor_per_time = factorPerTime;
	}

	public void setCost(ResourceType cost) {
		this.cost = cost;
	}

	public ResourceType getCost() {
		return cost;
	}
}
