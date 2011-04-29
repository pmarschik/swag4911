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
import swag49.model.BuildingType;
import swag49.model.BuildingLevel;
import swag49.model.ResourceType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class SquareDaoTest {
	// must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("squareDAO")
    private DataAccessObject<Square> squareDAO;
    
    @Autowired @Qualifier("baseDAO")
    private DataAccessObject<Base> baseDAO;
    
    @Autowired @Qualifier("mapDAO")
    private DataAccessObject<Map> mapDAO;
    
    @Autowired @Qualifier("tileDAO")
    private DataAccessObject<Tile> tileDAO;
    
    @Autowired @Qualifier("playerDAO")
    private DataAccessObject<Player> playerDAO;
    
    @Autowired @Qualifier("userDAO")
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
    	
//		Building building = new Building();
//		BuildingLevel buildingLevel = new BuildingLevel();
//		buildingLevel.setCost(ResourceType.CROPS);
//		buildingLevel.setFactor_per_time(1.0F);
//		buildingLevel.setLevel(1);
//		
//		building.setIsOfLevel(buildingLevel);
//		building.setType(BuildingType.BOW_SCHOOL);		
//    	square.setBuilding(building);
    	
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
    	
//		Building building = new Building();
//		BuildingLevel buildingLevel = new BuildingLevel();
//		buildingLevel.setCost(ResourceType.CROPS);
//		buildingLevel.setFactor_per_time(1.0F);
//		buildingLevel.setLevel(1);
//		
//		building.setIsOfLevel(buildingLevel);
//		building.setType(BuildingType.BOW_SCHOOL);		
//    	square.setBuilding(building);
    	
    	square = squareDAO.create(square);
    	
    	squareDAO.delete(square);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
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
    	
//		Building building = new Building();
//		BuildingLevel buildingLevel = new BuildingLevel();
//		buildingLevel.setCost(ResourceType.CROPS);
//		buildingLevel.setFactor_per_time(1.0F);
//		buildingLevel.setLevel(1);
//		
//		building.setIsOfLevel(buildingLevel);
//		building.setType(BuildingType.BOW_SCHOOL);		
//    	square.setBuilding(building);
    	
    	square = squareDAO.create(square);
    	
    	square = squareDAO.update(square);
    }
}
