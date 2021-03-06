package swag49.jobs;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.model.TroopAction;

public class TroopActionJob extends ActionJobBase<TroopAction> {

	private DataAccessObject<TroopAction, Long> troopActionDao;

    public void setTroopActionDao(DataAccessObject<TroopAction, Long> troopActionDao) {
        this.troopActionDao = troopActionDao;
    }

	@Override
	protected void doWork(TroopAction action, JobExecutionContext context) {
		mapLogic.handleAction(action);
	}

	@Override
	protected DataAccessObject<TroopAction, Long> getDAO() {
		return troopActionDao;
	}

}
