package swag49.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import swag49.dao.DataAccessObject;
import swag49.gamelogic.MapLogic;
import swag49.model.Action;

public abstract class ActionJobBase<T extends Action> extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(ActionJobBase.class);

    protected MapLogic mapLogic;

    public void setMapLogic(MapLogic mapLogic) {
        this.mapLogic = mapLogic;
    }


    @Override
    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        logger.info("Executing job with name {} and group {}.", context.getJobDetail().getName(), context.getJobDetail().getGroup());

        Long actionId = context.getJobDetail().getJobDataMap().getLong(
                "actionId");
        T action = getDAO().get(actionId);
        doWork(action, context);

        logger.info("Execution of job with name {} and group {} finished.", context.getJobDetail().getName(), context.getJobDetail().getGroup());
    }

    protected abstract void doWork(T action, JobExecutionContext context);

    protected abstract DataAccessObject<T, Long> getDAO();

}
