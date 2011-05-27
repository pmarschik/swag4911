package swag49.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BuildingLevel extends LevelBase {

	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;
		private Integer level;
		private Long buildingTypeId;

		public Id() {
			super();
		}

		public Id(int level, long buildingTypeId) {
			this.level = level;
			this.buildingTypeId = buildingTypeId;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Id) {
				return this.level.equals(((Id) obj).level)
						&& this.buildingTypeId
								.equals(((Id) obj).buildingTypeId);
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return level.hashCode() + buildingTypeId.hashCode();
		}
	}

	@EmbeddedId
	private Id id = new Id();

	@ManyToOne(optional = false)
	@JoinColumn(name = "buildingTypeId", insertable = false, updatable = false)
	private BuildingType buildingType;

	@AttributeOverrides({
			@AttributeOverride(name = "amount_gold", column = @Column(name = "production_gold")),
			@AttributeOverride(name = "amount_stone", column = @Column(name = "production_stone")),
			@AttributeOverride(name = "amount_wood", column = @Column(name = "production_wood")),
			@AttributeOverride(name = "amount_crops", column = @Column(name = "production_crops")) })
	private ResourceValue resourceProduction = new ResourceValue();
	
//	@ElementCollection
//	@MapKey(name = "resourceType")
//	@CollectionTable(name = "upkeepResourceTable", 
//	joinColumns = @JoinColumn(name = "string_id"))
//	private HashMap<String, Long> map = new HashMap<String, Long>();


	public BuildingLevel() {
	}

	public BuildingLevel(BuildingType buildingType, int level) {
		this.buildingType = buildingType;

		this.id.buildingTypeId = buildingType.getId();
		this.id.level = level;

		this.buildingType.getLevels().add(this);
	}

	public BuildingType getBuildingType() {
		return buildingType;
	}

	public Id getId() {
		return id;
	}

	@Override
	public Integer getLevel() {
		return id.level;
	}

	public ResourceValue getResourceProduction() {
		return resourceProduction;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public void setResourceProduction(ResourceValue resourceProduction) {
		this.resourceProduction = resourceProduction;
	}
}
