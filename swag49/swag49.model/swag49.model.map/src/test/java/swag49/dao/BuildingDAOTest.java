package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import swag49.model.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class BuildingDAOTest {

	// must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("buildingDAO")
    private DataAccessObject<Building, Square.Id> buildingDAO;

    @Autowired @Qualifier("buildingLevelDAO")
    private DataAccessObject<BuildingLevel, BuildingLevel.Id> buildingLevelDAO;

    @Autowired @Qualifier("buildingTypeDAO")
    private DataAccessObject<BuildingType, Long> buildingTypeDAO;

	// must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("squareDAO")
    private DataAccessObject<Square, Square.Id> squareDAO;

    @Autowired @Qualifier("baseDAO")
    private DataAccessObject<Base, Long> baseDAO;

    @Autowired @Qualifier("mapDAO")
    private DataAccessObject<Map, Long> mapDAO;

    @Autowired @Qualifier("tileDAO")
    private DataAccessObject<Tile, Tile.Id> tileDAO;

    @Autowired @Qualifier("playerDAO")
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

    	square = squareDAO.create(square);

    	BuildingType buildingType = new BuildingType();
		buildingType.setName("Baracks");

		buildingType = buildingTypeDAO.create(buildingType);

		BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);

		buildingLevel.setUpgradeDuration(Long.valueOf(60));
		buildingLevel.setBuildCosts(new ResourceValue(10, 0, 0, 0));
		buildingLevel.setResourceProduction(new ResourceValue(0, 0, 0, 1));
		buildingLevel.setUpkeepCosts(new ResourceValue(0, 0, 0, 0));

		buildingLevel = buildingLevelDAO.create(buildingLevel);

    	Building building = new Building(square);
    	building.setIsOfLevel(buildingLevel);
    	building.setType(buildingType);

    	System.out.println(building.getSquare().getId());
    	System.out.println(building.getId());
    	System.out.println(building.getIsOfLevel());
    	System.out.println(building.getType());


    	building = buildingDAO.create(building);
    }

    @Test
    public void delete_shouldDelete() throws Exception {
//
//		Map map = new Map();
//		map.setMaxUsers(5);
//
//		map = mapDAO.create(map);
//
//		Tile tile = new Tile(map, 1, 1);
//
//
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
//
//		Base base = new Base();
//		base.setHome(true);
//		base.setLocatedOn(tile);
//		base.setOwner(player);
//
//		base = baseDAO.create(base);
//
//    	Square square = new Square(base, 2);
//
//    	square = squareDAO.create(square);
//
//    	BuildingType buildingType = new BuildingType();
//    	buildingType.setName("test");
//
//    	buildingType = buildingTypeDAO.create(buildingType);
//
//    	BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);
//
//    	buildingLevel = buildingLevelDAO.create(buildingLevel);
//
//
//    	Building building = new Building(square);
//    	squareDAO.update(square);
//
//
//
//    	building = buildingDAO.create(building);
//
//    	buildingDAO.delete(building);
    }

    @Test
    public void update_shouldUpdate() throws Exception{
//
//		Map map = new Map();
//		map.setMaxUsers(5);
//
//		map = mapDAO.create(map);
//
//		Tile tile = new Tile(map, 1, 1);
//
//
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
//
//		Base base = new Base();
//		base.setHome(true);
//		base.setLocatedOn(tile);
//		base.setOwner(player);
//
//		base = baseDAO.create(base);
//
//    	Square square = new Square(base, 2);
//
//    	square = squareDAO.create(square);
//
//    	BuildingType buildingType = new BuildingType();
//    	buildingType.setName("test");
//
//    	buildingType = buildingTypeDAO.create(buildingType);
//
//    	BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);
//
//    	buildingLevel = buildingLevelDAO.create(buildingLevel);
//
//    	Building building = new Building(square);
//    	building.setIsOfLevel(buildingLevel);
//    	building.setType(buildingType);
//
//    	building = buildingDAO.create(building);
//
//    	building = buildingDAO.update(building);
    }
}
