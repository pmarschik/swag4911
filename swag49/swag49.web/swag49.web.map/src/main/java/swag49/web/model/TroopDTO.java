package swag49.web.model;

import swag49.model.Troop;

public class TroopDTO {

	private String name;
	private Integer level;
	private Integer strength;
	private Integer defense;
	private Integer speed;

	public TroopDTO(Troop troop) {
		this.setName(troop.getType().getName());
		this.setLevel(troop.getIsOfLevel().getLevel());
		this.setStrength(troop.getIsOfLevel().getStrength());
		this.setDefense(troop.getIsOfLevel().getDefense());
		this.setSpeed(troop.getIsOfLevel().getSpeed());
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getLevel() {
		return level;
	}

	public void setStrength(Integer strength) {
		this.strength = strength;
	}

	public Integer getStrength() {
		return strength;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}

	public Integer getDefense() {
		return defense;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	public Integer getSpeed() {
		return speed;
	}

}
