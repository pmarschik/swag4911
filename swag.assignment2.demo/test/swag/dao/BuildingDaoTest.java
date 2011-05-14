package swag.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import swag.model.Building;
import swag.model.BuildingType;
import swag.model.BuildingLevel;
//import swag.model.ResourceValue;
import swag.model.Square;
import swag.model.Base;
import swag.model.Map;
import swag.model.User;
import swag.model.Player;
import swag.model.Tile;
import swag.util.PersistenceHelper;

public class BuildingDaoTest {

	private PersistenceHelper persistenceHelper;

	private DataAccessObject<Building> buildingDAO;

	private DataAccessObject<BuildingLevel> buildingLevelDAO;

	private DataAccessObject<BuildingType> buildingTypeDAO;

	private DataAccessObject<Square> squareDAO;

	private DataAccessObject<Base> baseDAO;

	private DataAccessObject<Map> mapDAO;

	private DataAccessObject<Tile> tileDAO;

	private DataAccessObject<Player> playerDAO;

	private DataAccessObject<User> userDAO;

	public BuildingDaoTest() {
		persistenceHelper = PersistenceHelper.getInstance();
		baseDAO = new BaseDao(persistenceHelper.getEm());
		mapDAO = new MapDao(persistenceHelper.getEm());
		tileDAO = new TileDao(persistenceHelper.getEm());
		playerDAO = new PlayerDao(persistenceHelper.getEm());
		userDAO = new UserDao(persistenceHelper.getEm());
	}

	@Test
	public void create_shouldCreate() throws Exception {

//		Map map = new Map();
//		map.setMaxUsers(5);
//
//		map = mapDAO.create(map);
//
//		Tile tile = new Tile(map, 1, 1);
//
//		tile = tileDAO.create(tile);
//
//		User user = new User();
//		user.setLastName("testM");
//		user.setFirstName("testM");
//		user.setEmail("testemailM");
//		user.setPassword("testM");
//		user.setUsername("testM" + new Date().getTime());
//		user.setUtcOffset(0);
//
//		user = userDAO.create(user);
//
//		Player player = new Player();
//		player.setDeleted(false);
//		player.setOnline(true);
//		player.setUser(user);
//		player.setPlays(map);
//
//		player = playerDAO.create(player);
//
//		Base base = new Base();
//		base.setHome(true);
//		base.setLocatedOn(tile);
//		base.setOwner(player);
//
//		base = baseDAO.create(base);
//
//		Square square = new Square(base, 2);
//
//		square = squareDAO.create(square);
//
//		BuildingType buildingType = new BuildingType();
//		buildingType.setName("Baracks");
//
//		buildingType = buildingTypeDAO.create(buildingType);
//
//		BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);
//
//		buildingLevel.setUpgradeDuration(Long.valueOf(60));
//		buildingLevel.setBuildCosts(new ResourceValue(10, 0, 0, 0));
//		buildingLevel.setResourceProduction(new ResourceValue(0, 0, 0, 1));
//		buildingLevel.setUpkeepCosts(new ResourceValue(0, 0, 0, 0));
//
//		buildingLevel = buildingLevelDAO.create(buildingLevel);
//
//		Building building = new Building(square);
//		building.setIsOfLevel(buildingLevel);
//		building.setType(buildingType);
//
//		System.out.println(building.getSquare().getId());
//		System.out.println(building.getId());
//		System.out.println(building.getIsOfLevel());
//		System.out.println(building.getType());
//
//		building = buildingDAO.create(building);
	}

	@Test
	public void delete_shouldDelete() throws Exception {
		//
		// Map map = new Map();
		// map.setMaxUsers(5);
		//
		// map = mapDAO.create(map);
		//
		// Tile tile = new Tile(map, 1, 1);
		//
		//
		//
		// tile = tileDAO.create(tile);
		//
		// User user = new User();
		// user.setLastName("testM");
		// user.setFirstName("testM");
		// user.setEmail("testemailM");
		// user.setPassword("testM");
		// user.setUsername("testM" + new Date().getTime());
		// user.setUtcOffset(0);
		//
		// user = userDAO.create(user);
		//
		// Player player = new Player();
		// player.setDeleted(false);
		// player.setOnline(true);
		// player.setUser(user);
		// player.setPlays(map);
		//
		// player = playerDAO.create(player);
		//
		//
		// Base base = new Base();
		// base.setHome(true);
		// base.setLocatedOn(tile);
		// base.setOwner(player);
		//
		// base = baseDAO.create(base);
		//
		// Square square = new Square(base, 2);
		//
		// square = squareDAO.create(square);
		//
		// BuildingType buildingType = new BuildingType();
		// buildingType.setName("test");
		//
		// buildingType = buildingTypeDAO.create(buildingType);
		//
		// BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);
		//
		// buildingLevel = buildingLevelDAO.create(buildingLevel);
		//
		//
		// Building building = new Building(square);
		// squareDAO.update(square);
		//
		//
		//
		// building = buildingDAO.create(building);
		//
		// buildingDAO.delete(building);
	}

	@Test
	public void update_shouldUpdate() throws Exception {
		//
		// Map map = new Map();
		// map.setMaxUsers(5);
		//
		// map = mapDAO.create(map);
		//
		// Tile tile = new Tile(map, 1, 1);
		//
		//
		//
		// tile = tileDAO.create(tile);
		//
		// User user = new User();
		// user.setLastName("testM");
		// user.setFirstName("testM");
		// user.setEmail("testemailM");
		// user.setPassword("testM");
		// user.setUsername("testM" + new Date().getTime());
		// user.setUtcOffset(0);
		//
		// user = userDAO.create(user);
		//
		// Player player = new Player();
		// player.setDeleted(false);
		// player.setOnline(true);
		// player.setUser(user);
		// player.setPlays(map);
		//
		// player = playerDAO.create(player);
		//
		//
		// Base base = new Base();
		// base.setHome(true);
		// base.setLocatedOn(tile);
		// base.setOwner(player);
		//
		// base = baseDAO.create(base);
		//
		// Square square = new Square(base, 2);
		//
		// square = squareDAO.create(square);
		//
		// BuildingType buildingType = new BuildingType();
		// buildingType.setName("test");
		//
		// buildingType = buildingTypeDAO.create(buildingType);
		//
		// BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);
		//
		// buildingLevel = buildingLevelDAO.create(buildingLevel);
		//
		// Building building = new Building(square);
		// building.setIsOfLevel(buildingLevel);
		// building.setType(buildingType);
		//
		// building = buildingDAO.create(building);
		//
		// building = buildingDAO.update(building);
	}
}
