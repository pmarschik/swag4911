package swag.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Tile {

	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long mapId;
		private Integer x;
		private Integer y;

		public Id() {
			super();
		}

		public Id(long mapId, int x, int y) {
			super();
			this.mapId = mapId;
			this.x = x;
			this.y = y;
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
			if (mapId == null) {
				if (other.mapId != null)
					return false;
			} else if (!mapId.equals(other.mapId))
				return false;
			if (x == null) {
				if (other.x != null)
					return false;
			} else if (!x.equals(other.x))
				return false;
			if (y == null) {
				if (other.y != null)
					return false;
			} else if (!y.equals(other.y))
				return false;
			return true;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((mapId == null) ? 0 : mapId.hashCode());
			result = prime * result + ((x == null) ? 0 : x.hashCode());
			result = prime * result + ((y == null) ? 0 : y.hashCode());
			return result;
		}
	}

	@EmbeddedId
	private Id id = new Id();

	@SuppressWarnings("unused")
	@ManyToOne(optional=false)
	@JoinColumn(name = "mapId", insertable = false, updatable = false)
	private Map map;

	public Tile() {
	}

	public Tile(Map map, int x, int y) {
		this.map = map;
		this.id.mapId = map.getId();
		this.id.x = x;
		this.id.y = y;

		map.getConsistsOf().add(this);
	}
}
