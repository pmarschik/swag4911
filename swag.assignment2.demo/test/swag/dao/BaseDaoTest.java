package swag.dao;

import java.util.Date;

import org.junit.Test;

import swag.model.Base;
import swag.model.Map;
import swag.model.Player;
import swag.model.Tile;
import swag.model.User;
import swag.util.PersistenceHelper;

public class BaseDaoTest {

	private PersistenceHelper persistenceHelper;
	private DataAccessObject<Base> baseDAO;
	private DataAccessObject<Map> mapDAO;
	private DataAccessObject<Tile> tileDAO;
	private DataAccessObject<Player> playerDAO;
	private DataAccessObject<User> userDAO;
	
	public BaseDaoTest() {
		persistenceHelper = PersistenceHelper.getInstance();
		baseDAO = new BaseDao(persistenceHelper.getEm());
		mapDAO = new MapDao(persistenceHelper.getEm());
		tileDAO = new TileDao(persistenceHelper.getEm());
		playerDAO = new PlayerDao(persistenceHelper.getEm());
		userDAO = new UserDao(persistenceHelper.getEm());
	}

	@Test
	public void create_shouldCreate() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);
		map = mapDAO.create(map);

		Tile tile = new Tile();
		tile.setMap(map);
		tile.getId().setMapId(map.getId());
		tile.getId().setX(1);
		tile.getId().setY(1);
		map.getConsistsOf().add(tile);

		tile = tileDAO.create(tile);

		User user = new User();
		user.setLastName("testM");
		user.setFirstName("testM");
		user.setEmail("testemailM");
		user.setPassword("testM");
		user.setUsername("testM" + new Date().getTime());
		user.setUtcOffset(0);

		user = userDAO.create(user);

		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUser(user);
		player.setPlays(map);

		player = playerDAO.create(player);

		Base base = new Base();
		base.setHome(true);
		base.setLocatedOn(tile);
		base.setOwner(player);

		base = baseDAO.create(base);
	}

	@Test
	public void delete_shouldDelete() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);

		map = mapDAO.create(map);

		Tile tile = new Tile();
		tile.setMap(map);
		tile.getId().setMapId(map.getId());
		tile.getId().setX(1);
		tile.getId().setY(1);
		map.getConsistsOf().add(tile);


		tile = tileDAO.create(tile);

		User user = new User();
		user.setLastName("testM");
		user.setFirstName("testM");
		user.setEmail("testemailM");
		user.setPassword("testM");
		user.setUsername("testM" + new Date().getTime());
		user.setUtcOffset(0);

		user = userDAO.create(user);

		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUser(user);
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
	public void update_shouldUpdate() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);

		map = mapDAO.create(map);

		Tile tile = new Tile();
		tile.setMap(map);
		tile.getId().setMapId(map.getId());
		tile.getId().setX(1);
		tile.getId().setY(1);
		map.getConsistsOf().add(tile);


		tile = tileDAO.create(tile);

		User user = new User();
		user.setLastName("testM");
		user.setFirstName("testM");
		user.setEmail("testemailM");
		user.setPassword("testM");
		user.setUsername("testM" + new Date().getTime());
		user.setUtcOffset(0);

		user = userDAO.create(user);

		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUser(user);
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
