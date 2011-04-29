package swag.dao;

import java.util.Date;

import org.junit.Test;

import swag.model.Map;
import swag.model.Message;
import swag.model.Player;
import swag.model.User;
import swag.util.PersistenceHelper;


public class MessageDaoTest  {
    
	private PersistenceHelper persistenceHelper;
    private DataAccessObject<Message> messageDAO;
    private DataAccessObject<User> userDAO;
    private DataAccessObject<Map> mapDAO;
    private DataAccessObject<Player> playerDAO;
    
    public MessageDaoTest() {
		persistenceHelper = PersistenceHelper.getInstance();
		
		messageDAO = new MessageDao(persistenceHelper.getEm());
		mapDAO = new MapDao(persistenceHelper.getEm());
		playerDAO = new PlayerDao(persistenceHelper.getEm());
		userDAO = new UserDao(persistenceHelper.getEm());
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
		
		Message message = new Message();
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
		
		Message message = new Message();
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
		
		Message message = new Message();
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
