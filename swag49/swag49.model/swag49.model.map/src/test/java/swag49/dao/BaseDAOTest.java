package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Base;
import swag49.model.Map;
import swag49.model.Player;
import swag49.model.Tile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class BaseDAOTest {
	// must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("baseDAO")
    private DataAccessObject<Base, Long> baseDAO;

    @Autowired @Qualifier("mapDAO")
    private DataAccessObject<Map, Long> mapDAO;

    @Autowired @Qualifier("tileDAO")
    private DataAccessObject<Tile, Tile.Id> tileDAO;

    @Autowired @Qualifier("playerDAO")
    private DataAccessObject<Player, Long> playerDAO;

    @Test
    @Transactional("swag49.map")
    public void create_shouldCreate() throws Exception{
		Map map = new Map();
		map.setMaxUsers(5);
        map.setUrl("test");
		map = mapDAO.create(map);

		Tile tile = new Tile(map, 1, 1);


		tile = tileDAO.create(tile);

		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
        player.setUserId("max");
		player.setPlays(map);

		player = playerDAO.create(player);


		Base base = new Base();
		base.setHome(true);
		base.setLocatedOn(tile);
		base.setOwner(player);

		base = baseDAO.create(base);
    }

    @Test
    @Transactional("swag49.map")
    public void delete_shouldDelete()  throws Exception{
		Map map = new Map();
		map.setMaxUsers(5);
        map.setUrl("test");

		map = mapDAO.create(map);

		Tile tile = new Tile(map, 1, 1);
		tile = tileDAO.create(tile);

		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId("max");
		player.setPlays(map);

		player = playerDAO.create(player);


		Base base = new Base();
		base.setHome(true);
		base.setLocatedOn(tile);
		base.setOwner(player);

		base = baseDAO.create(base);

		baseDAO.delete(base);
    }

    @Test
    @Transactional("swag49.map")
    public void update_shouldUpdate() throws Exception{
		Map map = new Map();
		map.setMaxUsers(5);
        map.setUrl("test");

		map = mapDAO.create(map);

		Tile tile = new Tile(map, 1, 1);
		tile = tileDAO.create(tile);

		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId("max");
		player.setPlays(map);

		player = playerDAO.create(player);


		Base base = new Base();
		base.setHome(true);
		base.setLocatedOn(tile);
		base.setOwner(player);

		base = baseDAO.create(base);

		base.setHome(false);

		base = baseDAO.update(base);
    }
}
