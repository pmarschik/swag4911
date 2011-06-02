package swag49.listener;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;

import swag49.model.Action;

public abstract class ActionListenerBase {

	@Autowired
	private Scheduler scheduler;

	protected abstract Class<? extends Job> getJobClass();

	@PostRemove
	public void postRemove(Action action) throws SchedulerException {
		scheduler.deleteJob(getJobKey(action));
	}

	@PostPersist
	public void postPersist(Action action) throws SchedulerException {
		JobDetail jobDetail = newJob(getJobClass()).withIdentity(
				getJobKey(action)).usingJobData("actionId", action.getId())
				.build();

		Trigger trigger = newTrigger().withIdentity(action.getId().toString(),
				action.getClass().getName()).startAt(action.getEndDate())
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
	}

	private JobKey getJobKey(Action action) {
		return new JobKey(action.getId().toString(), action.getClass()
				.getName());
	}

	// private JobDataMap getJobDataMap(TroopAction action) {
	// JobDataMap jobData = new JobDataMap();
	// ArrayList<Long> troopIds = new ArrayList<Long>();
	// for (Troop troop : action.getConcerns()) {
	// troopIds.add(troop.getId());
	// }
	// jobData.put("actionId", action.getId());
	// jobData.put("tile", action.getTarget().getId());
	// jobData.put("player", action.getPlayer().getId());
	// return jobData;
	// }
}
