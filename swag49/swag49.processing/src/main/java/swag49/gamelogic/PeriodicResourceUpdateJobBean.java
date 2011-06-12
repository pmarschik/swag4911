package swag49.gamelogic;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 09.06.11
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class PeriodicResourceUpdateJobBean extends QuartzJobBean {

    private static final String APPLICATION_CONTEXT_KEY = "applicationContext";

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            ApplicationContext appContext = getApplicationContext(context);
            PeriodicResourceUpdateLogic logic = appContext.getBean("periodicResourceUpdateLogic", PeriodicResourceUpdateLogic.class);

            logic.updateResources();
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

    public ApplicationContext getApplicationContext(JobExecutionContext context) throws SchedulerException {
        SchedulerContext schedulerContext = context.getScheduler().getContext();
        ApplicationContext applicationContext = (ApplicationContext) schedulerContext.get(APPLICATION_CONTEXT_KEY);
        return applicationContext;
    }
}
