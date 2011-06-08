package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import swag49.model.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class SquareDAOTest {
	// must use interface, qualifier is optional, use only if several beans that
	// match interface
	@Autowired
	@Qualifier("squareDAO")
	private DataAccessObject<Square, Square.Id> squareDAO;

	@Autowired
	@Qualifier("baseDAO")
	private DataAccessObject<Base, Long> baseDAO;

	@Autowired
	@Qualifier("mapDAO")
	private DataAccessObject<Map, Long> mapDAO;

	@Autowired
	@Qualifier("tileDAO")
	private DataAccessObject<Tile, Tile.Id> tileDAO;

	@Autowired
	@Qualifier("playerDAO")
	private DataAccessObject<Player, Long> playerDAO;

	@Test
	public void create_shouldCreate() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);
        map.setUrl("test");

		map = mapDAO.create(map);

		Tile tile = new Tile(map, 1, 1);

		tile = tileDAO.create(tile);

		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(1L);
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
        map.setUrl("test");

		map = mapDAO.create(map);

		Tile tile = new Tile(map, 1, 1);

		tile = tileDAO.create(tile);
		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(1L);
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
        map.setUrl("test");

		map = mapDAO.create(map);

		Tile tile = new Tile(map, 1, 1);

		tile = tileDAO.create(tile);


		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(1L);
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
