package swag.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BuildingLevel {

	@Column(nullable = false)
	private Integer Level;

	@Column(nullable = false)
	private Float factor_per_time;

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
}
