package swag49.jobs;

import org.quartz.JobExecutionContext;

import swag49.model.BuildAction;

public class BuildActionJob extends ActionJobBase<BuildAction> {

	@Override
	protected void doWork(BuildAction action, JobExecutionContext context) {
		// TODO implement

	}

}
