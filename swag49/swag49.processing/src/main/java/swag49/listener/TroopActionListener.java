package swag49.listener;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.ArrayList;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;

import swag49.jobs.TroopActionJob;
import swag49.model.Troop;
import swag49.model.TroopAction;

public class TroopActionListener {

	@Autowired
	private Scheduler scheduler;

	@PostRemove
	public void postRemove(TroopAction action) throws SchedulerException {
		scheduler.deleteJob(getJobKey(action));
	}

	@PostPersist
	public void postPersist(TroopAction action) throws SchedulerException {

		JobDetail jobDetail = newJob(TroopActionJob.class).withIdentity(
				getJobKey(action)).usingJobData("actionId", action.getId())
				.build();

		Trigger trigger = newTrigger().withIdentity(action.getId().toString(),
				action.getClass().getName()).startAt(action.getEndDate())
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
	}

	private JobKey getJobKey(TroopAction action) {
		return new JobKey(action.getId().toString(), action.getClass()
				.getName());
	}

//	private JobDataMap getJobDataMap(TroopAction action) {
//		JobDataMap jobData = new JobDataMap();
//		ArrayList<Long> troopIds = new ArrayList<Long>();
//		for (Troop troop : action.getConcerns()) {
//			troopIds.add(troop.getId());
//		}
//		jobData.put("actionId", action.getId());
//		jobData.put("tile", action.getTarget().getId());
//		jobData.put("player", action.getPlayer().getId());
//		return jobData;
//	}
}
