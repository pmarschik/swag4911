package swag49.jobs;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import swag49.dao.DataAccessObject;
import swag49.model.TroopAction;

public class TroopActionJob extends ActionJobBase<TroopAction> {

	@Autowired
	@Qualifier("troopActionDAO")
	private DataAccessObject<TroopAction> troopActionDao;

	@Override
	protected void doWork(TroopAction action, JobExecutionContext context) {
		// TODO implement
	}

	@Override
	protected DataAccessObject<TroopAction> getDao() {
		return troopActionDao;
	}

}
