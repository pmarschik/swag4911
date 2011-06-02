package swag49.listener;

import org.quartz.Job;

import swag49.jobs.TroopActionJob;

public class TroopActionListener extends ActionListenerBase {

	@Override
	protected Class<? extends Job> getJobClass() {
		return TroopActionJob.class;
	}

}
