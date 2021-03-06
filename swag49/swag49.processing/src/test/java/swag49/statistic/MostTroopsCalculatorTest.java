package swag49.statistic;

import org.junit.Before;
import org.junit.runner.RunWith;
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
import swag49.statistics.MostTroopsCalculator;
import swag49.statistics.StatisticCalculator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class MostTroopsCalculatorTest {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Autowired
    @Qualifier("mostTroopsCalculator")
    private StatisticCalculator calculator;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Before
    public void setUp() {
        SimpleJdbcTemplate jt = new SimpleJdbcTemplate(dataSource);
        SimpleJdbcTestUtils.executeSqlScript(jt, new ClassPathResource("testdata_statistics.sql"), false);
    }

    @org.junit.Test
    @Transactional("swag49.map")
    public void execute_statisticNotYetPersisted_shouldPersistCorrectEntries() {
        calculator.calculate();

        TypedQuery<Statistic> query = em.createQuery("select statistic from Statistic statistic where statistic.name = :name", Statistic.class);
        query.setParameter("name", MostTroopsCalculator.NAME);
        Statistic statistic = query.getSingleResult();

        assertThat(statistic.getEntries().size(), is(5));
        StatisticEntry[] entries = statistic.getEntries().toArray(new StatisticEntry[statistic.getEntries().size()]);

        assertThat(entries[0].getRanking(), is(1));
        assertThat(entries[0].getPlayer().getId(), is(1L));
        assertThat(entries[0].getScore(), is(13));

        assertThat(entries[1].getRanking(), is(2));
        assertThat(entries[1].getPlayer().getId(), is(2L));
        assertThat(entries[1].getScore(), is(7));

        assertThat(entries[2].getRanking(), is(3));
        assertThat(entries[2].getPlayer().getId(), is(3L));
        assertThat(entries[2].getScore(), is(4));

        assertThat(entries[3].getRanking(), is(4));
        assertThat(entries[3].getPlayer().getId(), is(4L));
        assertThat(entries[3].getScore(), is(2));

        assertThat(entries[4].getRanking(), is(5));
        assertThat(entries[4].getPlayer().getId(), is(5L));
        assertThat(entries[4].getScore(), is(1));
    }

    @org.junit.Test
    @Transactional("swag49.map")
    public void execute_statisticAlreadyPersisted_shouldPersistCorrectEntries() {
        Statistic statistic = new Statistic();
        statistic.setName(MostTroopsCalculator.NAME);
        em.persist(statistic);

        execute_statisticNotYetPersisted_shouldPersistCorrectEntries();
    }
}
