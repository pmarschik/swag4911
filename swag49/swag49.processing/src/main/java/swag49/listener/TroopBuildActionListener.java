package swag49.listener;

import org.quartz.Job;
import swag49.jobs.TroopActionJob;
import swag49.jobs.TroopBuildActionJob;

public class TroopBuildActionListener extends ActionListenerBase {

	@Override
	protected Class<? extends Job> getJobClass() {
		return TroopBuildActionJob.class;
	}

}
