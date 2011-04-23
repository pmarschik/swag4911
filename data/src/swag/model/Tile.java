package swag.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
		public int hashCode() {
			return mapId.hashCode() + x.hashCode() + y.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Id) {
				return this.mapId.equals(((Id) obj).mapId)
						&& this.x.equals(((Id) obj).x)
						&& this.y.equals(((Id) obj).y);
			} else {
				return false;
			}
		}
	}

	@EmbeddedId
	private Id id = new Id();

	@ManyToOne(optional = false)
	@JoinColumn(name = "mapId", insertable = false, updatable = false)
	private Map map;

	@Enumerated(value = EnumType.STRING)
	private ResourceType special;

	public Tile() {
	}

	public Tile(Map map, int x, int y) {
		this.map = map;
		this.id.mapId = map.getId();
		this.id.x = x;
		this.id.y = y;

		map.getConsistsOf().add(this);
	}

	public Id getId() {
		return id;
	}

	public Map getMap() {
		return map;
	}

	public void setSpecial(ResourceType special) {
		this.special = special;
	}

	public ResourceType getSpecial() {
		return special;
	}
}
