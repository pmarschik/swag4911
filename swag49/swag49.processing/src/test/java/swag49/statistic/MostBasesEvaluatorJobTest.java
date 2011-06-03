package swag49.statistic;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import swag49.statistics.MostBasesEvaluatorJob;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class MostBasesEvaluatorJobTest {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    @Qualifier("mostBasesJob")
    private Job job;

    @org.junit.Test
    public void execute_statisticNotYetPersisted() throws JobExecutionException {
        JobExecutionContext context = Mockito.mock(JobExecutionContext.class);
        job.execute(context);
    }
}
