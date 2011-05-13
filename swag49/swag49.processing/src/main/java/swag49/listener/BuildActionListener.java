package swag49.listener;

import org.quartz.Job;

import swag49.jobs.BuildActionJob;

public class BuildActionListener extends ActionListenerBase {

	@Override
	protected Class<? extends Job> getJobClass() {
		// TODO Auto-generated method stub
		return BuildActionJob.class;
	}

}
