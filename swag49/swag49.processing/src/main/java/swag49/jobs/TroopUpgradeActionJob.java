package swag49.jobs;

import org.quartz.JobExecutionContext;
import swag49.dao.DataAccessObject;
import swag49.model.TroopUpgradeAction;

public class TroopUpgradeActionJob extends ActionJobBase<TroopUpgradeAction> {

    private DataAccessObject<TroopUpgradeAction, Long> troopUpgradeActionDao;

    public void setTroopUpgradeActionDao(DataAccessObject<TroopUpgradeAction, Long> troopUpgradeActionDao) {
        this.troopUpgradeActionDao = troopUpgradeActionDao;
    }

    @Override
    protected void doWork(TroopUpgradeAction action, JobExecutionContext context) {
        mapLogic.handleAction(action);
    }

    @Override
    protected DataAccessObject<TroopUpgradeAction, Long> getDAO() {
        return troopUpgradeActionDao;
    }

}
