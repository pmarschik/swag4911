package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Map;
import swag49.model.Player;
import swag49.model.Statistic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
@Transactional("swag49.map")
public class StatisticDAOTest {
    @PersistenceContext
    private EntityManager em;

    // must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired
    @Qualifier("statisticDAO")
    private DataAccessObject<Statistic, Long> statisticDAO;

    @Autowired
    @Qualifier("mapDAO")
    private DataAccessObject<Map, Long> mapDAO;

    @Autowired
    @Qualifier("playerDAO")
    private DataAccessObject<Player, Long> playerDAO;


    @Test

    public void create_shouldCreate() throws Exception {
        Statistic statistic = new Statistic();
        statistic.setName("Most defeats");
        statistic = statisticDAO.create(statistic);

        Statistic loadedStatistic = em.find(Statistic.class, statistic.getId());
        assertThat(loadedStatistic.getName(), is(statistic.getName()));
    }

    @Test
    public void update_shouldUpdate() throws Exception {
        Statistic statistic = new Statistic();
        statistic.setName("Most defeats");
        em.persist(statistic);

        statistic.setName("Most bases");
        statistic = statisticDAO.update(statistic);

        Statistic loadedStatistic = em.find(Statistic.class, statistic.getId());
        assertThat(loadedStatistic.getName(), is(statistic.getName()));
    }

    @Test
    public void delete_shouldDelete() throws Exception {
        Statistic statistic = new Statistic();
        statistic.setName("Most defeats");
        em.persist(statistic);

        statisticDAO.delete(statistic);

        assertThat(em.find(Statistic.class, statistic.getId()), is(nullValue()));
    }
}
