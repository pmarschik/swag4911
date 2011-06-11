package swag49.listener;

import org.quartz.*;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import swag49.model.Action;
import swag49.model.listener.ActionPersistenceEventListener;
import swag49.util.Log;


public abstract class ActionListenerBase<T extends Action> implements ActionPersistenceEventListener<T> {

    @Log
    private Logger logger;

    @Autowired
    private Scheduler scheduler;

    protected abstract Class<? extends Job> getJobClass();

    public void postRemove(T action) {
        try {
            scheduler.deleteJob(getJobId(action), getJobGroup(action));
        } catch (SchedulerException e) {
            logger.error("Error while deleting job.", e);
        }
    }

    public void postPersist(T action) {
        logger.info("Scheduling job for action {} of type {}", action.getId(), action.getClass().getSimpleName());

        JobDetail jobDetail = new JobDetail(getJobId(action), getJobGroup(action), getJobClass());
        jobDetail.getJobDataMap().put("actionId", action.getId());

        Trigger trigger = new SimpleTrigger(getJobId(action), getJobGroup(action), action.getEndDate());

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch(ObjectAlreadyExistsException e) {
            logger.info("Job is already existing.");
        } catch (SchedulerException e) {
            logger.error("Error while scheduling job.", e);
        }
    }

    private String getJobId(Action action) {
        return action.getId().toString();
    }

    private String getJobGroup(Action action) {
        return action.getClass().getName();
    }
}
