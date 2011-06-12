package swag49.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TroopBuildAction extends Action {

    @ManyToOne(optional = false)
    private TroopType troopType;

    @ManyToOne(optional = false)
    private TroopLevel troopLevel;

    private Integer amount;

    public TroopLevel getTroopLevel() {
        return troopLevel;
    }

    public void setTroopLevel(TroopLevel troopLevel) {
        this.troopLevel = troopLevel;
    }

    public TroopBuildAction() {

    }

    public TroopBuildAction(Player player, Tile tile, TroopType type, TroopLevel level, int amount, Long duration) {
        super();
        this.setPlayer(player);
        this.setTarget(tile);
        this.setDuration(duration);
        this.setTroopType(type);
        this.setAmount(Integer.valueOf(amount));
        this.setTroopLevel(level);

    }

    public TroopType getTroopType() {
        return troopType;
    }

    public void setTroopType(TroopType troopType) {
        this.troopType = troopType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
