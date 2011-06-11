import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.listener.TroopActionListener;
import swag49.model.Player;
import swag49.model.Tile;
import swag49.model.Troop;
import swag49.model.TroopAction;
import swag49.model.listener.ActionPersistenceEventListener;
import swag49.model.listener.ActionPersistenceEventRegistry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class TroopActionAspectTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired @Qualifier("troopActionDAO")
    private DataAccessObject<TroopAction, Long> troopActionDAO;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    @Qualifier("troopActionListener")
    private ActionPersistenceEventListener listener;

    @Before
    public void setUp() {
        SimpleJdbcTemplate jt = new SimpleJdbcTemplate(dataSource);
        SimpleJdbcTestUtils.executeSqlScript(jt, new ClassPathResource("testdata_statistics.sql"), true);
    }

    @Test
    @Transactional("swag49.map")
    public void perist_shouldScheduleJob() throws Exception {
        ActionPersistenceEventRegistry.getInstance(TroopAction.class).registerListener(listener);

        Troop troop = em.find(Troop.class, 1L);
        Tile source = em.find(Tile.class, new Tile.Id(1L, 1, 1));
        Tile target = em.find(Tile.class, new Tile.Id(1L, 5, 4));
        Player player = em.find(Player.class, 1L);

        TroopAction action = new TroopAction();
        action.getConcerns().add(troop);
        action.setSource(source);
        action.setTarget(target);
        action.setIsAbortable(false);
        action.setShouldFoundBase(true);
        action.setPlayer(player);
        action.setStartDate(new Date());
        action.setDuration(1000L);
        em.persist(action);
        em.flush();
    }
}
