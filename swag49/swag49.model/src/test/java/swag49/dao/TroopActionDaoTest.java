package swag49.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import swag49.model.Map;
import swag49.model.Tile;
import swag49.model.TroopAction;
import swag49.model.Player;
import swag49.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class TroopActionDaoTest {
    @Autowired @Qualifier("tileDAO")
    private DataAccessObject<Tile> tileDAO;
    
    @Autowired @Qualifier("mapDAO")
    private DataAccessObject<Map> mapDAO;
    
    @Autowired @Qualifier("troopActionDAO")
    private DataAccessObject<TroopAction> troopActionDAO;
    
    @Autowired @Qualifier("playerDAO")
    private DataAccessObject<Player> playerDAO;
    
    @Autowired @Qualifier("userDAO")
    private DataAccessObject<User> userDAO;

    @Test
    public void create_shouldCreate() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
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
		
		
		Tile tile = new Tile(map,1 ,1);
		tile = tileDAO.create(tile);
		
		TroopAction troopAction = new TroopAction();
		troopAction.setDuration(1);
		troopAction.setPlayer(player);
		troopAction.setStartDate(new Date());
		troopAction.setTarget(tile);
		
		troopAction = troopActionDAO.create(troopAction);
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
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
		
		Tile tile = new Tile(map,1 ,1);
		tile = tileDAO.create(tile);
		
		TroopAction troopAction = new TroopAction();
		troopAction.setDuration(1);
		troopAction.setPlayer(player);
		troopAction.setStartDate(new Date());
		troopAction.setTarget(tile);
		
		troopAction = troopActionDAO.create(troopAction);
		
		troopActionDAO.delete(troopAction);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
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
		
		Tile tile = new Tile(map,1 ,1);
		tile = tileDAO.create(tile);
		
		TroopAction troopAction = new TroopAction();
		troopAction.setDuration(1);
		troopAction.setPlayer(player);
		troopAction.setStartDate(new Date());
		troopAction.setTarget(tile);
		
		troopAction = troopActionDAO.create(troopAction);		
		troopAction.setDuration(2);
		
		troopAction = troopActionDAO.update(troopAction);
    }
}
