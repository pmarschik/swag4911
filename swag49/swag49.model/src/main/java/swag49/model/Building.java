package swag49.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Building {

	@Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;
		private Square.Id squareId;

		public Id() {
			super();
		}

		public Id(Square.Id squareId) {
			this.squareId = squareId;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Id) {
				return this.squareId.equals(((Id) obj).squareId);
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return squareId.hashCode();
		}
	}

	@EmbeddedId
	private Id id = new Id();
	
	@OneToOne(mappedBy = "building", optional = false)
	@JoinColumn(name = "squareId", insertable = false, updatable = false)
	private Square square;
	
	@ManyToOne(optional = false)
	private BuildingType type;
	
	@ManyToOne(optional = false)
	private BuildingLevel isOfLevel;
	
	public Building() {
	}

	public Building(Square square) {
		this.square = square;
		this.id.squareId = square.getId();

		square.setBuilding(this);
	}

	public Id getId() {
		return id;
	}

	public BuildingLevel getIsOfLevel() {
		return isOfLevel;
	}

	public Square getSquare() {
		return square;
	}

	public BuildingType getType() {
		return type;
	}
	
	public void setIsOfLevel(BuildingLevel isOfLevel) {
		this.isOfLevel = isOfLevel;
	}

	public void setType(BuildingType type) {
		this.type = type;
	}
}
