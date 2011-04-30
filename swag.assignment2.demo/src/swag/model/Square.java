package swag.model;

@javax.persistence.Entity
public class Square {

	@javax.persistence.Embeddable
	public static class Id implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private Long baseId;

		private Integer position;

		public Id() {
		}

		public Long getBaseId() {
			return baseId;
		}

		public Integer getPosition() {
			return position;
		}

		public void setBaseId(Long baseId) {
			this.baseId = baseId;
		}

		public void setPosition(Integer position) {
			this.position = position;
		}

		@Override
		public int hashCode() {
			return baseId.hashCode() + position.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Id) {
				Id other = (Id) obj;
				return this.baseId.equals(other.baseId)
						&& this.position.equals(other.position);
			} else {
				return false;
			}
		}
	}

	@javax.persistence.EmbeddedId
	private Id id = new Id();

	@javax.persistence.ManyToOne(optional = false)
	@javax.persistence.JoinColumn(name = "baseId", insertable = false, updatable = false)
	private Base base;

	@javax.persistence.Embedded
	private Building building;

	public Square() {
	}

	public Id getId() {
		return id;
	}

	public Base getBase() {
		return base;
	}

	public Building getBuilding() {
		return building;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

}
