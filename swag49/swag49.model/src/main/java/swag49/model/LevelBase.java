package swag49.model;

import javax.persistence.Column;

public abstract class LevelBase {

	@Column(nullable = false)
	private Long upgradeDuration;

	@Column(nullable = false)
	private Integer level;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setUpgradeDuration(Long upgradeDuration) {
		this.upgradeDuration = upgradeDuration;
	}

	public Long getUpgradeDuration() {
		return upgradeDuration;
	}

}
