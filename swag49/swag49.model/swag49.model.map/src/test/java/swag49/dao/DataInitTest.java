package swag49.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import swag49.model.Base;
import swag49.model.Map;
import swag49.model.Player;
import swag49.model.Square;
import swag49.model.Tile;
import swag49.model.TroopType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class DataInitTest  {
    
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
	
	@Autowired @Qualifier("troopTypeDAO")
	private DataAccessObject<TroopType> troopTypeDAO;



    @Test
    public void InitData() throws Exception {
    	// 5 Players
		
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(1L);
		player.setPlays(map);
		
		player = playerDAO.create(player);
		
		player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(2L);
		player.setPlays(map);
		
		player = playerDAO.create(player);
		
		player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(3L);
		player.setPlays(map);
		
		player = playerDAO.create(player);
		
		player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(4L);
		player.setPlays(map);
		
		player = playerDAO.create(player);

		player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId(5L);
		player.setPlays(map);
		
		player = playerDAO.create(player);
		
		// 3 Trooptypes
		
    	TroopType troopType = new TroopType();    	
    	troopType.setName("Troop Type 1");    	
    	troopType = troopTypeDAO.create(troopType);
    	
    	troopType = new TroopType();    	
    	troopType.setName("Troop Type 2");    	
    	troopType = troopTypeDAO.create(troopType);
    	
    	troopType = new TroopType();    	
    	troopType.setName("Troop Type 3");    	
    	troopType = troopTypeDAO.create(troopType);
		
    	// 5 x 5 Tiles
    	for(int c = 1; c <= 5; c++)
    	{
    		for(int d = 1; c <= 5; c++)
    		{
    			Tile tile = new Tile(map,1 ,1);    			
    			tile = tileDAO.create(tile);
    		}
    	}
    	
    	//To be done

    }
    
  
}