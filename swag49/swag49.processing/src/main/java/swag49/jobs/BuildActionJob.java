package swag49.jobs;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import swag49.dao.DataAccessObject;
import swag49.model.BuildAction;

public class BuildActionJob extends ActionJobBase<BuildAction> {

	@Autowired
	@Qualifier("buildActionDAO")
	private DataAccessObject<BuildAction> buildActionDao;
	
	@Override
	protected void doWork(BuildAction action, JobExecutionContext context) {
		// TODO implement

	}

	@Override
	protected DataAccessObject<BuildAction> getDao() {
		return buildActionDao;
	}

}
