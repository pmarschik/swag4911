package swag.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Square {

	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long baseId;
		private Integer position;

		public Id() {
			super();
		}

		public Id(long baseId, int position) {
			super();
			this.baseId = baseId;
			this.position = position;
		}

		@Override
		public int hashCode() {
			return baseId.hashCode() + position.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Id) {
				return this.position.equals(((Id) obj).position)
						&& this.position.equals(((Id) obj).position);
			} else {
				return false;
			}
		}
	}

	@EmbeddedId
	private Id id = new Id();

	@ManyToOne(optional = false)
	@JoinColumn(name = "baseId", insertable = false, updatable = false)
	private Base base;
	
	@Embedded
	private Building building;

	public Square() {
	}

	public Square(Base base, int position) {
		this.base = base;
		this.id.baseId = base.getId();
		this.id.position = position;

		base.getConsistsOf().add(this);
	}
	
	public Id getId() {
		return id;
	}

	public Base getBase() {
		return base;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public Building getBuilding() {
		return building;
	}
}
