package swag.model;

@javax.persistence.Entity
public class Resource {

	@javax.persistence.Embeddable
	public static class Id implements java.io.Serializable {
		private static final long serialVersionUID = 1L;

		private Long playerId;

		@javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
		private ResourceType resourceType;

		public Id() {
		}

		public Long getPlayerId() {
			return playerId;
		}

		public ResourceType getResourceType() {
			return resourceType;
		}

		public void setPlayerId(Long playerId) {
			this.playerId = playerId;
		}

		public void setResourceType(ResourceType resourceType) {
			this.resourceType = resourceType;
		}

		@Override
		public int hashCode() {
			return playerId.hashCode() + resourceType.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Id) {
				Id other = (Id) obj;
				return this.playerId.equals(other.playerId)
						&& this.resourceType.equals(other.resourceType);
			} else {
				return false;
			}
		}
	}

	@javax.persistence.EmbeddedId
	private Id id = new Id();

	@javax.persistence.ManyToOne(optional = false)
	@javax.persistence.JoinColumn(name = "playerId", insertable = false, updatable = false)
	private Player player;

	@javax.persistence.Column(nullable = false)
	private Integer amount;

	public Resource() {
	}

	public Id getId() {
		return id;
	}

	public Player getPlayer() {
		return player;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
