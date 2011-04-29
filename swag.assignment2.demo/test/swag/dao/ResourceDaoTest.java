package swag.dao;

import java.util.Date;

import org.junit.Test;

import swag.model.Map;
import swag.model.Player;
import swag.model.Resource;
import swag.model.ResourceType;
import swag.model.User;
import swag.util.PersistenceHelper;

public class ResourceDaoTest {
  
	private PersistenceHelper persistenceHelper;
	
	private DataAccessObject<User> userDAO;
    private DataAccessObject<Map> mapDAO;
    private DataAccessObject<Player> playerDAO;
    private DataAccessObject<Resource> resourceDAO;
    
    public ResourceDaoTest() {
    	persistenceHelper = PersistenceHelper.getInstance();
    	
    	userDAO = new UserDao(persistenceHelper.getEm());
    	mapDAO = new MapDao(persistenceHelper.getEm());
    	playerDAO = new PlayerDao(persistenceHelper.getEm());
    	resourceDAO = new ResourceDao(persistenceHelper.getEm());
    }
    
    @Test
    public void create_shouldCreate() throws Exception {
		User user = new User();
		user.setLastName("testM");
		user.setFirstName("testM");
		user.setEmail("testemailM");
		user.setPassword("testM");
		user.setUsername("testM" + new Date().getTime());
		user.setUtcOffset(0);

		user = userDAO.create(user);
		
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUser(user);
		player.setPlays(map);
		
		player = playerDAO.create(player);	
		
		Resource resource = new Resource();
		resource.setPlayer(player);
		player.getResources().add(resource);
		resource.setAmount(100);
		resource.getId().setPlayerId(player.getId());
		resource.getId().setResourceType(ResourceType.GOLD);
		
		resource = resourceDAO.create(resource);
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
		User user = new User();
		user.setLastName("testM");
		user.setFirstName("testM");
		user.setEmail("testemailM");
		user.setPassword("testM");
		user.setUsername("testM" + new Date().getTime());
		user.setUtcOffset(0);

		user = userDAO.create(user);
		
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUser(user);
		player.setPlays(map);
		
		player = playerDAO.create(player);	
		
		Resource resource = new Resource();
		resource.setPlayer(player);
		player.getResources().add(resource);
		resource.setAmount(100);
		resource.getId().setPlayerId(player.getId());
		resource.getId().setResourceType(ResourceType.GOLD);
		
		resource = resourceDAO.create(resource);
		
		resourceDAO.delete(resource);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
		User user = new User();
		user.setLastName("testM");
		user.setFirstName("testM");
		user.setEmail("testemailM");
		user.setPassword("testM");
		user.setUsername("testM" + new Date().getTime());
		user.setUtcOffset(0);

		user = userDAO.create(user);
		
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUser(user);
		player.setPlays(map);
		
		player = playerDAO.create(player);	
		
		Resource resource = new Resource();
		resource.setPlayer(player);
		player.getResources().add(resource);
		resource.setAmount(100);
		resource.getId().setPlayerId(player.getId());
		resource.getId().setResourceType(ResourceType.GOLD);
		
		resource = resourceDAO.create(resource);
		
		resource.setAmount(500);
		
		resource = resourceDAO.update(resource);
    }
}
