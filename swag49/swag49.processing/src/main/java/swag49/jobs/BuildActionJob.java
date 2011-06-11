package swag49.jobs;

import org.quartz.JobExecutionContext;
import swag49.dao.DataAccessObject;
import swag49.model.BuildAction;

public class BuildActionJob extends ActionJobBase<BuildAction> {

    private DataAccessObject<BuildAction, Long> buildActionDao;

    public void setBuildActionDao(DataAccessObject<BuildAction, Long> buildActionDao) {
        this.buildActionDao = buildActionDao;
    }

    @Override
    protected void doWork(BuildAction action, JobExecutionContext context) {
        mapLogic.handleAction(action);
    }

    @Override
    protected DataAccessObject<BuildAction, Long> getDAO() {
        return buildActionDao;
    }

}
