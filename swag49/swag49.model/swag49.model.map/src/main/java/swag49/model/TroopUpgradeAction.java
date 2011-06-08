package swag49.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TroopUpgradeAction extends Action {

    @OneToOne
    private Troop troop;

    @ManyToOne(optional = false)
    private TroopLevel troopLevel;

    public TroopUpgradeAction() {

    }

    public TroopUpgradeAction(Player player, Troop troop, TroopLevel troopLevel, Long duration) {
        this.setPlayer(player);
        this.setTroop(troop);
        this.setTroopLevel(troopLevel);
        this.setDuration(duration);
    }

    public Troop getTroop() {
        return troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }

    public TroopLevel getTroopLevel() {
        return troopLevel;
    }

    public void setTroopLevel(TroopLevel troopLevel) {
        this.troopLevel = troopLevel;
    }
}
