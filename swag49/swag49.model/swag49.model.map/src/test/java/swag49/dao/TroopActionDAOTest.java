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
import swag49.model.Tile;
import swag49.model.TroopAction;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class TroopActionDAOTest {
    @Autowired
    @Qualifier("tileDAO")
    private DataAccessObject<Tile, Tile.Id> tileDAO;

    @Autowired
    @Qualifier("mapDAO")
    private DataAccessObject<Map, Long> mapDAO;

    @Autowired
    @Qualifier("troopActionDAO")
    private DataAccessObject<TroopAction, Long> troopActionDAO;

    @Autowired
    @Qualifier("playerDAO")
    private DataAccessObject<Player, Long> playerDAO;

    @Test
    @Transactional("swag49.map")
    public void create_shouldCreate() throws Exception {
        Map map = new Map();
        map.setMaxUsers(5);
        map.setUrl("test");

        map = mapDAO.create(map);

        Player player = new Player();
        player.setDeleted(false);
        player.setOnline(true);
        player.setUserId("max");
        player.setPlays(map);

        player = playerDAO.create(player);


        Tile tile = new Tile(map, 1, 1);
        tile = tileDAO.create(tile);

        TroopAction troopAction = new TroopAction();
        troopAction.setDuration(1L);
        troopAction.setPlayer(player);
        troopAction.setStartDate(new Date());
        troopAction.setTarget(tile);

        troopAction = troopActionDAO.create(troopAction);
    }

    @Test
    @Transactional("swag49.map")
    public void delete_shouldDelete() throws Exception {
        Map map = new Map();
        map.setMaxUsers(5);
        map.setUrl("test");

        map = mapDAO.create(map);

        Player player = new Player();
        player.setDeleted(false);
        player.setOnline(true);
        player.setUserId("max");
        player.setPlays(map);

        player = playerDAO.create(player);

        Tile tile = new Tile(map, 1, 1);
        tile = tileDAO.create(tile);

        TroopAction troopAction = new TroopAction();
        troopAction.setDuration(1L);
        troopAction.setPlayer(player);
        troopAction.setStartDate(new Date());
        troopAction.setTarget(tile);

        troopAction = troopActionDAO.create(troopAction);

        troopActionDAO.delete(troopAction);
    }

    @Test
    @Transactional("swag49.map")
    public void update_shouldUpdate() throws Exception {
        Map map = new Map();
        map.setMaxUsers(5);
        map.setUrl("test");

        map = mapDAO.create(map);

        Player player = new Player();
        player.setDeleted(false);
        player.setOnline(true);
        player.setUserId("max");
        player.setPlays(map);

        player = playerDAO.create(player);

        Tile tile = new Tile(map, 1, 1);
        tile = tileDAO.create(tile);

        TroopAction troopAction = new TroopAction();
        troopAction.setDuration(1L);
        troopAction.setPlayer(player);
        troopAction.setStartDate(new Date());
        troopAction.setTarget(tile);

        troopAction = troopActionDAO.create(troopAction);
        troopAction.setDuration(2L);

        troopAction = troopActionDAO.update(troopAction);
    }
}
