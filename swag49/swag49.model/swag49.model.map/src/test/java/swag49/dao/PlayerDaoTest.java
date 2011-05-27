package swag49.dao;

import java.util.Date;
import swag49.model.Map;
import swag49.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class PlayerDaoTest {
    // must use interface, qualifier is optional, use only if several beans that match interface
    
    @Autowired @Qualifier("mapDAO")
    private DataAccessObject<Map> mapDAO;
    
    @Autowired @Qualifier("playerDAO")
    private DataAccessObject<Player> playerDAO;
    

    @Test
    public void create_shouldCreate() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(1L);
		player.setPlays(map);
		
		player = playerDAO.create(player);		
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(1L);
		player.setPlays(map);
		
		player = playerDAO.create(player);
		
		playerDAO.delete(player); 
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(1L);
		player.setPlays(map);
		
		player = playerDAO.create(player);
		
		player.setOnline(false);
		
		playerDAO.update(player); 
    }
}
