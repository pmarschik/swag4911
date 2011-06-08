package swag49.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TroopBuildAction extends Action {

    @ManyToOne(optional=false)
	private TroopType troopType;

    private int amount;

    public TroopType getTroopType() {
        return troopType;
    }

    public void setTroopType(TroopType troopType) {
        this.troopType = troopType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
