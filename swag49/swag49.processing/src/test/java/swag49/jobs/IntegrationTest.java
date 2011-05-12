package swag49.jobs;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class IntegrationTest {

    @Autowired @Qualifier("scheduler")
    private Scheduler scheduler;
    
    @Autowired @Qualifier("dataSource")
    private DataSource dataSource;
    
    @Before
    public void setUp() {
    	SimpleJdbcTemplate jt = new SimpleJdbcTemplate(dataSource);
		SimpleJdbcTestUtils.executeSqlScript(jt , new ClassPathResource("tables_postgres.sql"), true);
    }
    
    @Test
    public void test() throws SchedulerException {
    	JobDetail jobDetail = newJob(TestJob.class).withIdentity(
				"testJob", "group").build();

		Trigger trigger = newTrigger().withIdentity("testTrigger", "group").startNow()
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
    }
}
