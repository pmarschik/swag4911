package swag.dao;

import org.junit.Test;

import swag.model.Map;
import swag.util.PersistenceHelper;

public class MapDaoTest {
	
	private PersistenceHelper persistence;
	private DataAccessObject<Map> mapDAO;
	
	public MapDaoTest() {
		persistence = PersistenceHelper.getInstance();
		mapDAO = new MapDao(persistence.getEm());
	}

    @Test
    public void create_shouldCreate() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		mapDAO.delete(map);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
		Map map = new Map();
		map.setMaxUsers(5);
		
		map = mapDAO.create(map);
		
		map.setMaxUsers(1);
		
		mapDAO.update(map);
    }
}
