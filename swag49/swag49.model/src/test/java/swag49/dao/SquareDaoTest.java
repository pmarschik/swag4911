package swag49.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import swag49.model.Building;
import swag49.model.Square;
import swag49.model.Base;
import swag49.model.Tile;
import swag49.model.Map;
import swag49.model.Player;
import swag49.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class SquareDaoTest {
	// must use interface, qualifier is optional, use only if several beans that
	// match interface
	@Autowired
	@Qualifier("squareDAO")
	private DataAccessObject<Square> squareDAO;

	@Autowired
	@Qualifier("baseDAO")
	private DataAccessObject<Base> baseDAO;

	@Autowired
	@Qualifier("mapDAO")
	private DataAccessObject<Map> mapDAO;

	@Autowired
	@Qualifier("tileDAO")
	private DataAccessObject<Tile> tileDAO;

	@Autowired
	@Qualifier("playerDAO")
	private DataAccessObject<Player> playerDAO;

	@Autowired
	@Qualifier("userDAO")
	private DataAccessObject<User> userDAO;

	@Test
	public void create_shouldCreate() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);

		map = mapDAO.create(map);

		Tile tile = new Tile(map, 1, 1);

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

		Square square = new Square(base, 2);

		squareDAO.create(square);
	}

	@Test
	public void delete_shouldDelete() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);

		map = mapDAO.create(map);

		Tile tile = new Tile(map, 1, 1);

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

		Square square = new Square(base, 2);

		square = squareDAO.create(square);

		squareDAO.delete(square);
	}

	@Test
	public void update_shouldUpdate() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);

		map = mapDAO.create(map);

		Tile tile = new Tile(map, 1, 1);

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

		Square square = new Square(base, 2);

		square = squareDAO.create(square);

		throw new Exception("HIER FEHLT NOCH WAS");

//		square = squareDAO.update(square);
	}
}
