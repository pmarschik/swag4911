package swag49.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Troop {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private Integer strength;

	@Column(nullable = false)
	private Integer speed;

	@Column(nullable = false)
	private Integer defense;

	@Column(nullable = false)
	private Integer level;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TroopType type;

	@ManyToOne
	private Tile position;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setType(TroopType type) {
		this.type = type;
	}

	public TroopType getType() {
		return type;
	}

	public void setPosition(Tile position) {
		this.position = position;
	}

	public Tile getPosition() {
		return position;
	}

}
