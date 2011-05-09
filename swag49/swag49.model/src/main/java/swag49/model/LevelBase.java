package swag49.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class LevelBase {

	@Column(nullable = false)
	private Long upgradeDuration;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount_gold", column = @Column(name = "upkeep_gold")),
			@AttributeOverride(name = "amount_stone", column = @Column(name = "upkeep_stone")),
			@AttributeOverride(name = "amount_wood", column = @Column(name = "upkeep_wood")),
			@AttributeOverride(name = "amount_crops", column = @Column(name = "upkeep_crops")) })
	private ResourceValue upkeepCosts = new ResourceValue();

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount_gold", column = @Column(name = "cost_gold")),
			@AttributeOverride(name = "amount_stone", column = @Column(name = "cost_stone")),
			@AttributeOverride(name = "amount_wood", column = @Column(name = "cost_wood")),
			@AttributeOverride(name = "amount_crops", column = @Column(name = "cost_crops")) })
	private ResourceValue buildCosts = new ResourceValue();

	public ResourceValue getBuildCosts() {
		return buildCosts;
	}

	public abstract Integer getLevel();

	public Long getUpgradeDuration() {
		return upgradeDuration;
	}

	public ResourceValue getUpkeepCosts() {
		return upkeepCosts;
	}

	public void setBuildCosts(ResourceValue buildCosts) {
		this.buildCosts = buildCosts;
	}

	public void setUpgradeDuration(Long upgradeDuration) {
		this.upgradeDuration = upgradeDuration;
	}

	public void setUpkeepCosts(ResourceValue upkeepCosts) {
		this.upkeepCosts = upkeepCosts;
	}

}
