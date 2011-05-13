package swag49.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import swag49.dao.DataAccessObject;
import swag49.model.TroopAction;

public class TroopActionJob implements Job {

	@Autowired @Qualifier("troopActionDAO")
	private DataAccessObject<TroopAction> troopActionDao;
	
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Long actionId = context.getJobDetail().getJobDataMap().getLong("actionId");
		TroopAction action = troopActionDao.get(actionId);
		//TODO to work with action.
	}
	
}
