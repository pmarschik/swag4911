package swag49.model;

import java.io.Serializable;

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
		public int hashCode() {
			return level.hashCode() + buildingTypeId.hashCode();
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
	}
	
	public BuildingLevel() {
	}
	
	public BuildingLevel(BuildingType buildingType, int level) {
		this.buildingType = buildingType;
		
		this.id.buildingTypeId = buildingType.getId();
		this.id.level = level;
		
		this.buildingType.getLevels().add(this);
	}

	@EmbeddedId
	private Id id = new Id();

	@ManyToOne(optional = false)
	@JoinColumn(name = "buildingTypeId", insertable = false, updatable = false)
	private BuildingType buildingType;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public BuildingType getBuildingType() {
		return buildingType;
	}

	public void setBuildingType(BuildingType buildingType) {
		this.buildingType = buildingType;
	}

	@Override
	public Integer getLevel() {
		return id.level;
	}
}
