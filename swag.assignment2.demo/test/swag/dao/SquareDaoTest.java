package swag.dao;

import java.util.Date;

import org.junit.Test;

import swag.model.Base;
import swag.model.Building;
import swag.model.BuildingLevel;
import swag.model.BuildingType;
import swag.model.Map;
import swag.model.Player;
import swag.model.ResourceType;
import swag.model.Square;
import swag.model.Tile;
import swag.model.User;
import swag.util.PersistenceHelper;

public class SquareDaoTest {

	private PersistenceHelper persistenceHelper;

	private DataAccessObject<Square> squareDAO;
	private DataAccessObject<Base> baseDAO;
	private DataAccessObject<Map> mapDAO;
	private DataAccessObject<Tile> tileDAO;
	private DataAccessObject<Player> playerDAO;
	private DataAccessObject<User> userDAO;

	public SquareDaoTest() {
		persistenceHelper = PersistenceHelper.getInstance();
		
		squareDAO = new SquareDao(persistenceHelper.getEm());
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

		Square square = new Square();
		square.setBase(base);
		base.getConsistsOf().add(square);
		square.getId().setBaseId(base.getId());
		square.getId().setPosition(2);

		Building building = new Building();
		BuildingLevel buildingLevel = new BuildingLevel();
		buildingLevel.setCost(ResourceType.CROPS);
		buildingLevel.setFactor_per_time(1.0F);
		buildingLevel.setLevel(1);

		building.setIsOfLevel(buildingLevel);
		building.setType(BuildingType.BOW_SCHOOL);
		square.setBuilding(building);

		squareDAO.create(square);
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

		Square square = new Square();
		square.setBase(base);
		base.getConsistsOf().add(square);
		square.getId().setBaseId(base.getId());
		square.getId().setPosition(2);

		Building building = new Building();
		BuildingLevel buildingLevel = new BuildingLevel();
		buildingLevel.setCost(ResourceType.CROPS);
		buildingLevel.setFactor_per_time(1.0F);
		buildingLevel.setLevel(1);

		building.setIsOfLevel(buildingLevel);
		building.setType(BuildingType.BOW_SCHOOL);
		square.setBuilding(building);

		square = squareDAO.create(square);

		squareDAO.delete(square);
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

		Square square = new Square();
		square.setBase(base);
		base.getConsistsOf().add(square);
		square.getId().setBaseId(base.getId());
		square.getId().setPosition(2);

		Building building = new Building();
		BuildingLevel buildingLevel = new BuildingLevel();
		buildingLevel.setCost(ResourceType.CROPS);
		buildingLevel.setFactor_per_time(1.0F);
		buildingLevel.setLevel(1);

		building.setIsOfLevel(buildingLevel);
		building.setType(BuildingType.BOW_SCHOOL);

		square.setBuilding(building);

		square = squareDAO.create(square);

		square = squareDAO.update(square);
	}
}
