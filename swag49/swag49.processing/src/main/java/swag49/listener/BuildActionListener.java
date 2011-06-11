package swag49.listener;

import org.quartz.Job;
import org.springframework.stereotype.Component;
import swag49.jobs.BuildActionJob;
import swag49.model.BuildAction;


@Component("buildActionListener")
public class BuildActionListener extends ActionListenerBase<BuildAction> {

    @Override
    protected Class<? extends Job> getJobClass() {
        return BuildActionJob.class;
    }

}
