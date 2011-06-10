package swag49.web.model;

import swag49.model.Troop;

public class TroopDTO {

    private String name;
    private Integer level;
    private Integer strength;
    private Integer defense;
    private Integer cargo_capacity;
    private Integer speed;
    private Boolean canUpgrade;
    private ResourceValueDTO upgradeCost;
    private Long troopId;

    public Long getTroopId() {
        return troopId;
    }

    public void setTroopId(Long troopId) {
        this.troopId = troopId;
    }

    public Boolean getCanUpgrade() {
        return canUpgrade;
    }

    public void setCanUpgrade(Boolean canUpgrade) {
        this.canUpgrade = canUpgrade;
    }

    public ResourceValueDTO getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(ResourceValueDTO upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public TroopDTO(Troop troop) {
        this.setName(troop.getType().getName());
        this.setLevel(troop.getIsOfLevel().getLevel());
        this.setStrength(troop.getIsOfLevel().getStrength());
        this.setDefense(troop.getIsOfLevel().getDefense());
        this.setSpeed(troop.getIsOfLevel().getSpeed());
        this.setCargo_capacity(troop.getIsOfLevel().getCargo_capacity());
        this.troopId = troop.getId();
    }

    public Integer getCargo_capacity() {
        return cargo_capacity;
    }

    public void setCargo_capacity(Integer cargo_capacity) {
        this.cargo_capacity = cargo_capacity;
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
