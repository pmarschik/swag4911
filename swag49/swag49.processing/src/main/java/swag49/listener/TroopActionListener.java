package swag49.listener;

import org.quartz.Job;

import org.springframework.stereotype.Component;
import swag49.jobs.TroopActionJob;
import swag49.model.TroopAction;

@Component("troopActionListener")
public class TroopActionListener extends ActionListenerBase<TroopAction> {

	@Override
	protected Class<? extends Job> getJobClass() {
		return TroopActionJob.class;
	}

}
