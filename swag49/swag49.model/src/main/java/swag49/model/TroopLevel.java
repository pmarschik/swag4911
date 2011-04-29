package swag49.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TroopLevel extends LevelBase {

	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;
		private Integer level;
		private Long troopTypeId;

		public Id() {
			super();
		}

		public Id(int level, long troopTypeId) {
			this.level = level;
			this.troopTypeId = troopTypeId;
		}

		@Override
		public int hashCode() {
			return level.hashCode() + troopTypeId.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Id) {
				return this.level.equals(((Id) obj).level)
						&& this.troopTypeId.equals(((Id) obj).troopTypeId);
			} else {
				return false;
			}
		}
	}

	public TroopLevel() {
	}

	public TroopLevel(TroopType troopType, int level) {
		this.setTroopType(troopType);

		this.id.troopTypeId = troopType.getId();
		this.id.level = level;

		this.getTroopType().getLevels().add(this);
	}

	@EmbeddedId
	private Id id = new Id();

	@ManyToOne(optional = false)
	@JoinColumn(name = "troopTypeId", insertable = false, updatable = false)
	private TroopType troopType;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public void setTroopType(TroopType troopType) {
		this.troopType = troopType;
	}

	public TroopType getTroopType() {
		return troopType;
	}

	@Override
	public Integer getLevel() {
		return id.level;
	}

}
