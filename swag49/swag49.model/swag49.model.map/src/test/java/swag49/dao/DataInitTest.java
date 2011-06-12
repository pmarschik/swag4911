package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class DataInitTest  {

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

	@Autowired @Qualifier("troopTypeDAO")
	private DataAccessObject<TroopType, Long> troopTypeDAO;



    @Test
    @Transactional("swag49.map")
    public void InitData() throws Exception {
    	// 5 Players

		Map map = new Map();
		map.setMaxUsers(5);
        map.setUrl("test");

		map = mapDAO.create(map);

		Player player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId("max");
		player.setPlays(map);

		player = playerDAO.create(player);

		player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId("hans");
		player.setPlays(map);

		player = playerDAO.create(player);

		player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId("lisa");
		player.setPlays(map);

		player = playerDAO.create(player);

		player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId("john");
		player.setPlays(map);

		player = playerDAO.create(player);

		player = new Player();
		player.setDeleted(false);
		player.setOnline(true);
		player.setUserId("carl");
		player.setPlays(map);

		player = playerDAO.create(player);

		// 3 Trooptypes

    	TroopType troopType = new TroopType();
    	troopType.setName("Troop Type 1");
        troopType.setCanFoundBase(false);
    	troopType = troopTypeDAO.create(troopType);

    	troopType = new TroopType();
    	troopType.setName("Troop Type 2");
        troopType.setCanFoundBase(false);
    	troopType = troopTypeDAO.create(troopType);

    	troopType = new TroopType();
    	troopType.setName("Troop Type 3");
        troopType.setCanFoundBase(true);
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
