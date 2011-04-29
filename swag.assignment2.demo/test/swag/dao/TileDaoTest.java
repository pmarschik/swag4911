package swag.dao;

import org.junit.Test;

import swag.model.Map;
import swag.model.ResourceType;
import swag.model.Tile;
import swag.util.PersistenceHelper;

public class TileDaoTest {

	private PersistenceHelper persistenceHelper;
	
	private DataAccessObject<Tile> tileDAO;
    private DataAccessObject<Map> mapDAO;
    
    public TileDaoTest() {
    	persistenceHelper = PersistenceHelper.getInstance();
		
    	mapDAO = new MapDao(persistenceHelper.getEm());
		tileDAO = new TileDao(persistenceHelper.getEm());
    }

    @Test
    public void create_shouldCreate() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		Tile tile = new Tile();
		tile.setMap(map);
		tile.getId().setMapId(map.getId());
		tile.getId().setX(1);
		tile.getId().setY(1);
		map.getConsistsOf().add(tile);
		
		tile = tileDAO.create(tile);
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		Tile tile = new Tile();
		tile.setMap(map);
		tile.getId().setMapId(map.getId());
		tile.getId().setX(1);
		tile.getId().setY(1);
		map.getConsistsOf().add(tile);
		tile = tileDAO.create(tile);
		
		tileDAO.delete(tile);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		Tile tile = new Tile();
		tile.setMap(map);
		tile.getId().setMapId(map.getId());
		tile.getId().setX(1);
		tile.getId().setY(1);
		map.getConsistsOf().add(tile);
		tile = tileDAO.create(tile);
		
		tile.setSpecial(ResourceType.GOLD);
		
		tile = tileDAO.update(tile);
    }
}
