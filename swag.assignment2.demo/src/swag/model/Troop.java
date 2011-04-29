package swag.model;

@javax.persistence.Entity
public class Troop {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	private Long id;

	@javax.persistence.Column(nullable = false)
	private Integer strength;

	@javax.persistence.Column(nullable = false)
	private Integer speed;

	@javax.persistence.Column(nullable = false)
	private Integer defense;

	@javax.persistence.Column(nullable = false)
	private Integer level;

	@javax.persistence.Enumerated(javax.persistence.EnumType.STRING)
	@javax.persistence.Column(nullable = false)
	private TroopType type;

	@javax.persistence.ManyToOne()
	private Tile position;

	public Troop() {
	}

	public Long getId() {
		return id;
	}

	public Integer getStrength() {
		return strength;
	}

	public Integer getSpeed() {
		return speed;
	}

	public Integer getDefense() {
		return defense;
	}

	public Integer getLevel() {
		return level;
	}

	public TroopType getType() {
		return type;
	}

	public Tile getPosition() {
		return position;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setType(TroopType type) {
		this.type = type;
	}

	public void setPosition(Tile position) {
		this.position = position;
	}

}
