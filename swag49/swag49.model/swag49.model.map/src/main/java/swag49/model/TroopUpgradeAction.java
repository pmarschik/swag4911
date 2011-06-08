package swag49.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TroopUpgradeAction extends Action {

    @OneToOne
    private Troop troop;

    @ManyToOne(optional = false)
    private TroopLevel newLevel;

    public Troop getTroop() {
        return troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }

    public TroopLevel getNewLevel() {
        return newLevel;
    }

    public void setNewLevel(TroopLevel newLevel) {
        this.newLevel = newLevel;
    }
}
