package swag49.dao;

import org.junit.Assert;
import org.junit.Before;
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
import swag49.model.StatisticEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class StatisticEntryDAOTest {

    @PersistenceContext
    private EntityManager em;

    // must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired
    @Qualifier("statisticEntryDAO")
    private DataAccessObject<StatisticEntry, StatisticEntry.Id> statisticEntryDAO;

    private Statistic statistic;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() {
        createTestdata();
    }

    private void createTestdata() {
        Map map = new Map();
        map.setMaxUsers(5);
        map.setUrl("test");
        em.persist(map);

        player1 = new Player();
        player1.setDeleted(false);
        player1.setOnline(true);
        player1.setUserId(1L);
        player1.setPlays(map);
        em.persist(player1);

        player2 = new Player();
        player2.setDeleted(false);
        player2.setOnline(true);
        player2.setUserId(2L);
        player2.setPlays(map);
        em.persist(player1);

        statistic = new Statistic();
        statistic.setName("Most defeats");
        em.persist(statistic);
    }

    @Test
    @Transactional("swag49.map")
    public void create_shouldCreate() throws Exception {
        StatisticEntry entry1 = new StatisticEntry(statistic, 1);
        entry1.setPlayer(player1);
        entry1.setScore(100);
        statistic.getEntries().add(entry1);
        statisticEntryDAO.create(entry1);

        StatisticEntry entry2 = new StatisticEntry(statistic, 2);
        entry2.setPlayer(player2);
        entry2.setScore(50);
        statistic.getEntries().add(entry2);
        statisticEntryDAO.create(entry2);

        StatisticEntry loadedEntry = em.find(StatisticEntry.class, entry1.getId());
        Assert.assertEquals(loadedEntry.getRanking(), new Integer(1));
        Assert.assertEquals(loadedEntry.getScore(), new Integer(100));
        Assert.assertEquals(loadedEntry.getPlayer().getId(), player1.getId());

        loadedEntry = em.find(StatisticEntry.class, entry2.getId());
        Assert.assertEquals(loadedEntry.getRanking(), new Integer(2));
        Assert.assertEquals(loadedEntry.getScore(), new Integer(50));
        Assert.assertEquals(loadedEntry.getPlayer().getId(), player2.getId());
    }

    @Test
    @Transactional("swag49.map")
    public void update_shouldUpdate() throws Exception {
        StatisticEntry entry1 = new StatisticEntry(statistic, 1);
        entry1.setPlayer(player1);
        entry1.setScore(100);
        statistic.getEntries().add(entry1);
        em.persist(entry1);

        entry1.setScore(1000);
        entry1.setPlayer(player2);
        entry1 = statisticEntryDAO.update(entry1);

        StatisticEntry loadedEntry = em.find(StatisticEntry.class, entry1.getId());
        assertThat(loadedEntry.getScore(), is(entry1.getScore()));
        assertThat(loadedEntry.getPlayer().getId(), is(player2.getId()));
    }


    @Test
    @Transactional("swag49.map")
    public void delete_shouldDelete() throws Exception {
        StatisticEntry entry1 = new StatisticEntry(statistic, 1);
        entry1.setPlayer(player1);
        entry1.setScore(100);
        statistic.getEntries().add(entry1);
        em.persist(entry1);

        statisticEntryDAO.delete(entry1);
        assertThat(em.find(StatisticEntry.class, entry1.getId()), is(nullValue()));
    }
}
