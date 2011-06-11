package swag49.transfer.model;

public class TroopDTO {
    private String name;
    private int level;
    private int strength;
    private int defense;
    private int cargo_capacity;
    private int speed;
    private boolean canUpgrade;
    private ResourceValueDTO upgradeCost;
    private long id;
    private boolean active;

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getCanUpgrade() {
        return canUpgrade;
    }

    public void setCanUpgrade(boolean canUpgrade) {
        this.canUpgrade = canUpgrade;
    }

    public ResourceValueDTO getUpgradeCost() {
        return upgradeCost;
    }

    public void setUpgradeCost(ResourceValueDTO upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public TroopDTO(String troopTypeName, int level, int strength, int defense, int speed, int cargoCapacity, long id, boolean active) {
        this.setName(troopTypeName);
        this.setLevel(level);
        this.setStrength(strength);
        this.setDefense(defense);
        this.setSpeed(speed);
        this.setCargo_capacity(cargoCapacity);
        this.id = id;
        this.setActive(active);
    }

    public int getCargo_capacity() {
        return cargo_capacity;
    }

    public void setCargo_capacity(int cargo_capacity) {
        this.cargo_capacity = cargo_capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDefense() {
        return defense;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
