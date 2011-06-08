package swag49.jobs;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import swag49.dao.DataAccessObject;
import swag49.gamelogic.TroopActionLogic;
import swag49.model.TroopAction;

public class TroopActionJob extends ActionJobBase<TroopAction> {

	@Autowired
	@Qualifier("troopActionDAO")
	private DataAccessObject<TroopAction, Long> troopActionDao;

	@Override
	protected void doWork(TroopAction action, JobExecutionContext context) {
		TroopActionLogic logic = new TroopActionLogic();
		logic.handleAction(action);
	}

	@Override
	protected DataAccessObject<TroopAction, Long> getDAO() {
		return troopActionDao;
	}

}
