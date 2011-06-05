package swag49.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
			if (obj != null && obj instanceof Id) {
				return this.mapId.equals(((Id) obj).mapId)
						&& this.x.equals(((Id) obj).x)
						&& this.y.equals(((Id) obj).y);
			} else {
				return false;
			}
		}

		public Long getMapId() {
			return mapId;
		}

		public void setMapId(Long mapId) {
			this.mapId = mapId;
		}

		public Integer getX() {
			return x;
		}

		public void setX(Integer x) {
			this.x = x;
		}

		public Integer getY() {
			return y;
		}

		public void setY(Integer y) {
			this.y = y;
		}

		@Override
		public int hashCode() {
			return mapId.hashCode() + x.hashCode() + y.hashCode();
		}
	}

	@EmbeddedId
	private Id id = new Id();

	@ManyToOne(optional = false)
	@JoinColumn(name = "mapId", insertable = false, updatable = false)
	private Map map;

	@Enumerated(value = EnumType.STRING)
	private ResourceType special;

	@OneToMany(mappedBy = "position")
	private Set<Troop> troops;

	@OneToOne(optional = true)
	private Base base;

	public Base getBase() {
		return base;
	}

	public void setBase(Base base) {
		this.base = base;
	}

	public Tile() {
	}

	public Tile(Map map, int x, int y) {
		this.map = map;
        this.id = new Id(map.getId(),x,y);

		map.getConsistsOf().add(this);
	}

	public Id getId() {
		return id;
	}

	public Map getMap() {
		return map;
	}

	public ResourceType getSpecial() {
		return special;
	}

	public Set<Troop> getTroops() {
		return troops;
	}

	public void setSpecial(ResourceType special) {
		this.special = special;
	}

	public void setTroops(Set<Troop> troops) {
		this.troops = troops;
	}
}
