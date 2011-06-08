package swag49.jobs;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import swag49.dao.DataAccessObject;
import swag49.model.TroopAction;
import swag49.model.TroopBuildAction;

public class TroopBuildActionJob extends ActionJobBase<TroopBuildAction> {

	@Autowired
	@Qualifier("troopBuildActionDAO")
	private DataAccessObject<TroopBuildAction> troopBuildActionDao;

	@Override
	protected void doWork(TroopBuildAction action, JobExecutionContext context) {
		mapLogic.handleAction(action);
	}

	@Override
	protected DataAccessObject<TroopBuildAction> getDao() {
		return troopBuildActionDao;
	}

}
