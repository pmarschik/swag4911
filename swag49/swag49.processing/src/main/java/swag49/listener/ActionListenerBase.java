package swag49.listener;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import swag49.model.Action;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;

public abstract class ActionListenerBase {

    @Autowired
    private Scheduler scheduler;

    protected abstract Class<? extends Job> getJobClass();

    @PostRemove
    public void postRemove(Action action) throws SchedulerException {
        scheduler.deleteJob(getJobId(action), getJobGroup(action));
    }

    @PostPersist
    public void postPersist(Action action) throws SchedulerException {
        JobDetail jobDetail = new JobDetail(getJobId(action), getJobGroup(action), getJobClass());
        jobDetail.getJobDataMap().put("actionId", action.getId());

        Trigger trigger = new SimpleTrigger(getJobId(action), getJobGroup(action), action.getEndDate());

        scheduler.scheduleJob(jobDetail, trigger);
    }

    private String getJobId(Action action) {
        return action.getId().toString();
    }

    private String getJobGroup(Action action) {
        return action.getClass().getName();
    }
}
