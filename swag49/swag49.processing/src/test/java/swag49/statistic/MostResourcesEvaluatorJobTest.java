package swag49.statistic;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Statistic;
import swag49.model.StatisticEntry;
import swag49.statistics.MostBasesEvaluatorJob;
import swag49.statistics.MostResourcesEvaluatorJob;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class MostResourcesEvaluatorJobTest {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    @Qualifier("mostResourcesJob")
    private Job job;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    JobExecutionContext context;

    @Before
    public void setUp() {
        SimpleJdbcTemplate jt = new SimpleJdbcTemplate(dataSource);
        SimpleJdbcTestUtils.executeSqlScript(jt, new ClassPathResource("testdata_statistics.sql"), false);
        context = Mockito.mock(JobExecutionContext.class);
    }

    @org.junit.Test
    @Transactional
    public void execute_statisticNotYetPersisted_shouldPersistCorrectEntries() throws JobExecutionException {
        job.execute(context);

        TypedQuery<Statistic> query = em.createQuery("select statistic from Statistic statistic where statistic.name = :name", Statistic.class);
        query.setParameter("name", MostResourcesEvaluatorJob.NAME);
        Statistic statistic = query.getSingleResult();

        assertThat(statistic.getEntries().size(), is(5));
        StatisticEntry[] entries = statistic.getEntries().toArray(new StatisticEntry[statistic.getEntries().size()]);

        assertThat(entries[0].getRanking(), is(1));
        assertThat(entries[0].getPlayer().getId(), is(3L));
        assertThat(entries[0].getScore(), is(61788));

        assertThat(entries[1].getRanking(), is(2));
        assertThat(entries[1].getPlayer().getId(), is(2L));
        assertThat(entries[1].getScore(), is(51936));

        assertThat(entries[2].getRanking(), is(3));
        assertThat(entries[2].getPlayer().getId(), is(1L));
        assertThat(entries[2].getScore(), is(18465));

        assertThat(entries[3].getRanking(), is(4));
        assertThat(entries[3].getPlayer().getId(), is(5L));
        assertThat(entries[3].getScore(), is(1008));

        assertThat(entries[4].getRanking(), is(5));
        assertThat(entries[4].getPlayer().getId(), is(4L));
        assertThat(entries[4].getScore(), is(469));
    }

    @org.junit.Test
    @Transactional
    public void execute_statisticAlreadyPersisted_shouldPersistCorrectEntries() throws JobExecutionException {
        Statistic statistic = new Statistic();
        statistic.setName(MostResourcesEvaluatorJob.NAME);
        em.persist(statistic);

        execute_statisticNotYetPersisted_shouldPersistCorrectEntries();
    }
}
