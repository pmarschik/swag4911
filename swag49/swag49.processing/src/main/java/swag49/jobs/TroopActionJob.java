package swag49.jobs;

import org.quartz.JobExecutionContext;

import swag49.model.TroopAction;

public class TroopActionJob extends ActionJobBase<TroopAction> {

	@Override
	protected void doWork(TroopAction action, JobExecutionContext context) {
		// TODO implement
	}

}
