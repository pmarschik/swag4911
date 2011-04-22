package swag.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
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
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((baseId == null) ? 0 : baseId.hashCode());
			result = prime * result
					+ ((position == null) ? 0 : position.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Id other = (Id) obj;
			if (baseId == null) {
				if (other.baseId != null)
					return false;
			} else if (!baseId.equals(other.baseId))
				return false;
			if (position == null) {
				if (other.position != null)
					return false;
			} else if (!position.equals(other.position))
				return false;
			return true;
		}
	}

	@EmbeddedId
	private Id id = new Id();

	@SuppressWarnings("unused")
	@ManyToOne(optional = false)
	@JoinColumn(name = "baseId", insertable = false, updatable = false)
	private Base base;
	
	public Square(Base base, int position) {
		this.base = base;
		this.id.baseId = base.getId();
		this.id.position = position;

		base.getConsistsOf().add(this);
	}
}
