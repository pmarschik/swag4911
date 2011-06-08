package swag49.jobs;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import swag49.dao.DataAccessObject;
import swag49.model.TroopAction;
import swag49.model.TroopUpgradeAction;

public class TroopUpgradeActionJob extends ActionJobBase<TroopUpgradeAction> {

	@Autowired
	@Qualifier("troopUpgradeActionDAO")
	private DataAccessObject<TroopUpgradeAction, Long> troopUpgradeActionDao;

	@Override
	protected void doWork(TroopUpgradeAction action, JobExecutionContext context) {
		mapLogic.handleAction(action);
	}

	@Override
	protected DataAccessObject<TroopUpgradeAction, Long> getDAO() {
		return troopUpgradeActionDao;
	}

}
