package swag49.listener;

import org.quartz.Job;
import org.springframework.stereotype.Component;
import swag49.jobs.TroopUpgradeActionJob;
import swag49.model.TroopUpgradeAction;

@Component("troopUpgradeActionListener")
public class TroopUpgradeActionListener extends ActionListenerBase<TroopUpgradeAction> {

	@Override
	protected Class<? extends Job> getJobClass() {
		return TroopUpgradeActionJob.class;
	}

}
