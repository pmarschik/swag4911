package swag49.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import swag49.dao.DataAccessObject;
import swag49.model.Action;

public abstract class ActionJobBase<T extends Action> implements Job {

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		Long actionId = context.getJobDetail().getJobDataMap().getLong(
				"actionId");
		T action = getDao().get(actionId);
		doWork(action, context);
	}

	protected abstract void doWork(T action, JobExecutionContext context);
	
	protected abstract DataAccessObject<T> getDao();

}
