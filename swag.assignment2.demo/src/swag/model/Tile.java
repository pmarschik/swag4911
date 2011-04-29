package swag.model;

@javax.persistence.Entity
public class Tile {

	@javax.persistence.Embeddable
	public static class Id implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private Long mapId;

		private Integer x;

		private Integer y;

		public Id() {
		}

		public Long getMapId() {
			return mapId;
		}

		public Integer getX() {
			return x;
		}

		public Integer getY() {
			return y;
		}

		public void setMapId(Long mapId) {
			this.mapId = mapId;
		}

		public void setX(Integer x) {
			this.x = x;
		}

		public void setY(Integer y) {
			this.y = y;
		}

		@Override
		public int hashCode() {
			return mapId.hashCode() + x.hashCode() + y.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Id) {
				Id other = (Id) obj;
				return this.mapId.equals(other.mapId) && this.x.equals(other.x)
						&& this.y.equals(other.y);
			} else {
				return false;
			}
		}
	}

	@javax.persistence.EmbeddedId
	private Id id = new Id();

	@javax.persistence.ManyToOne(optional = false)
	@javax.persistence.JoinColumn(name = "mapId", insertable = false, updatable = false)
	private Map map;

	@javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
	private ResourceType special;

	@javax.persistence.OneToMany(mappedBy = "position")
	private java.util.Set<Troop> troops = new java.util.HashSet<Troop>();

	public Tile() {
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

	public java.util.Set<Troop> getTroops() {
		return troops;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void setSpecial(ResourceType special) {
		this.special = special;
	}

	public void setTroops(java.util.Set<Troop> troops) {
		this.troops = troops;
	}

}
