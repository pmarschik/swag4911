package swag49.dao;

import java.util.Date;

import swag49.model.ResourceType;
import swag49.model.Map;
import swag49.model.Player;
import swag49.model.Message;
import swag49.model.User;
import swag49.model.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class ResourceDaoTest {
    // must use interface, qualifier is optional, use only if several beans that match interface
    
    @Autowired @Qualifier("userDAO")
    private DataAccessObject<User> userDAO;
    
    @Autowired @Qualifier("mapDAO")
    private DataAccessObject<Map> mapDAO;
    
    @Autowired @Qualifier("playerDAO")
    private DataAccessObject<Player> playerDAO;
    
    @Autowired @Qualifier("resourceDAO")
    private DataAccessObject<Resource> resourceDAO;
    
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
		
		Resource resource = new Resource(player, ResourceType.GOLD, 100);
		
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
		
		Resource resource = new Resource(player, ResourceType.GOLD, 100);
		
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
		
		Resource resource = new Resource(player, ResourceType.GOLD, 100);
		
		resource = resourceDAO.create(resource);
		
		resource.setAmount(500);
		
		resource = resourceDAO.update(resource);
    }
}
