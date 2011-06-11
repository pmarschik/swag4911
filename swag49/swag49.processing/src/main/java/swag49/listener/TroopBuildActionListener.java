package swag49.listener;

import org.quartz.Job;
import org.springframework.stereotype.Component;
import swag49.jobs.TroopBuildActionJob;
import swag49.model.TroopBuildAction;

@Component("troopBuildActionListener")
public class TroopBuildActionListener extends ActionListenerBase<TroopBuildAction> {

	@Override
	protected Class<? extends Job> getJobClass() {
		return TroopBuildActionJob.class;
	}

}
