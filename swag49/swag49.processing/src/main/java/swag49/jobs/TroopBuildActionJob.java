package swag49.jobs;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import swag49.dao.DataAccessObject;
import swag49.model.TroopBuildAction;

public class TroopBuildActionJob extends ActionJobBase<TroopBuildAction> {

	private DataAccessObject<TroopBuildAction, Long> troopBuildActionDao;

    public void setTroopBuildActionDao(DataAccessObject<TroopBuildAction, Long> troopBuildActionDao) {
        this.troopBuildActionDao = troopBuildActionDao;
    }

    @Override
	protected void doWork(TroopBuildAction action, JobExecutionContext context) {
		mapLogic.handleAction(action);
	}

     @Override
	protected DataAccessObject<TroopBuildAction, Long> getDAO() {
		return troopBuildActionDao;
	}

}
