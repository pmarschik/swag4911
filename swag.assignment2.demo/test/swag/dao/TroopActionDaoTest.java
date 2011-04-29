package swag.dao;

import java.util.Date;

import org.junit.Test;

import swag.model.Map;
import swag.model.Player;
import swag.model.Tile;
import swag.model.TroopAction;
import swag.model.User;
import swag.util.PersistenceHelper;

public class TroopActionDaoTest {
	
	private PersistenceHelper persistenceHelper;

	private DataAccessObject<Tile> tileDAO;
    private DataAccessObject<Map> mapDAO;
    private DataAccessObject<TroopAction> troopActionDAO;
    private DataAccessObject<Player> playerDAO;
    private DataAccessObject<User> userDAO;
    
    public TroopActionDaoTest() {
		persistenceHelper = PersistenceHelper.getInstance();
	
		tileDAO = new TileDao(persistenceHelper.getEm());
		mapDAO = new MapDao(persistenceHelper.getEm());
		troopActionDAO = new TroopActionDao(persistenceHelper.getEm());
		playerDAO = new PlayerDao(persistenceHelper.getEm());
		userDAO = new UserDao(persistenceHelper.getEm());
	}

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
		
		
		Tile tile = new Tile();
		tile.setMap(map);
		tile.getId().setMapId(map.getId());
		tile.getId().setX(1);
		tile.getId().setY(1);
		map.getConsistsOf().add(tile);
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
		
		Tile tile = new Tile();
		tile.setMap(map);
		tile.getId().setMapId(map.getId());
		tile.getId().setX(1);
		tile.getId().setY(1);
		map.getConsistsOf().add(tile);
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
		
		Tile tile = new Tile();
		tile.setMap(map);
		tile.getId().setMapId(map.getId());
		tile.getId().setX(1);
		tile.getId().setY(1);
		map.getConsistsOf().add(tile);
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
