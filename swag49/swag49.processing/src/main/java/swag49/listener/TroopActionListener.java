package swag49.listener;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.persistence.PostPersist;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;

import swag49.jobs.TroopActionJob;
import swag49.model.TroopAction;

public class TroopActionListener {

	@Autowired
	private Scheduler scheduler;

	@PostPersist
	public void postPersist(TroopAction action) throws SchedulerException {
		JobDetail jobDetail = newJob(TroopActionJob.class).withIdentity(
				action.getId().toString(), action.getClass().getName()).build();

		Trigger trigger = newTrigger().withIdentity(action.getId().toString(),
				action.getClass().getName()).startAt(action.getEndDate())
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
	}
}
