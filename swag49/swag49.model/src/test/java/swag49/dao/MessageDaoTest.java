package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import swag49.model.Map;
import swag49.model.Player;
import swag49.model.Message;
import swag49.model.User;
import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class MessageDaoTest  {
    // must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("messageDAO")
    private DataAccessObject<Message> messageDAO;
    
    @Autowired @Qualifier("userDAO")
    private DataAccessObject<User> userDAO;
    
    @Autowired @Qualifier("mapDAO")
    private DataAccessObject<Map> mapDAO;
    
    @Autowired @Qualifier("playerDAO")
    private DataAccessObject<Player> playerDAO;
    

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
		
		swag49.model.Message message = new Message();
		message.setContent("test");
		message.setReceiver(player);
		message.setSender(player);
		message.setSubject("test");
		message.setSendDate(new Date());
		
		message = messageDAO.create(message);
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
		User user = new User();
		user.setLastName("testM2");
		user.setFirstName("testM2");
		user.setEmail("testemailM2");
		user.setPassword("testM2");
		user.setUsername("testM2" + new Date().getTime());
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
		
		swag49.model.Message message = new Message();
		message.setContent("test");
		message.setReceiver(player);
		message.setSender(player);
		message.setSubject("test");
		message.setSendDate(new Date());
		
		message = messageDAO.create(message);
		
		messageDAO.delete(message);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
		User user = new User();
		user.setLastName("testM3");
		user.setFirstName("testM3");
		user.setEmail("testemailM3");
		user.setPassword("testM3");
		user.setUsername("testM3" + new Date().getTime());
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
		
		swag49.model.Message message = new Message();
		message.setContent("test");
		message.setReceiver(player);
		message.setSender(player);
		message.setSubject("test");
		message.setSendDate(new Date());
		
		message = messageDAO.create(message);
		
		message.setSubject("New Subject");
		
		messageDAO.update(message);
    }
}