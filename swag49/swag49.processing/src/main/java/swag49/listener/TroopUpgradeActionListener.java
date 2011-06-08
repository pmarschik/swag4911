package swag49.listener;

import org.quartz.Job;
import swag49.jobs.TroopActionJob;
import swag49.jobs.TroopUpgradeActionJob;

public class TroopUpgradeActionListener extends ActionListenerBase {

	@Override
	protected Class<? extends Job> getJobClass() {
		return TroopUpgradeActionJob.class;
	}

}
