package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.transaction.annotation.Transactional;
import swag49.model.ResourceType;
import swag49.model.Map;
import swag49.model.Tile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class TileDAOTest {
    @Autowired @Qualifier("tileDAO")
    private DataAccessObject<Tile, Tile.Id> tileDAO;

    @Autowired @Qualifier("mapDAO")
    private DataAccessObject<Map, Long> mapDAO;

    @Test
    @Transactional("swag49.map")
    public void create_shouldCreate() throws Exception {
		Map map = new Map();
        map.setUrl("test");
		map.setMaxUsers(5);

		map = mapDAO.create(map);

		Tile tile = new Tile(map,1 ,1);
		tile = tileDAO.create(tile);
    }

    @Test
    @Transactional("swag49.map")
    public void delete_shouldDelete() throws Exception {
		Map map = new Map();
        map.setUrl("test");
		map.setMaxUsers(5);

		map = mapDAO.create(map);

		Tile tile = new Tile(map,1 ,1);
		tile = tileDAO.create(tile);

		tileDAO.delete(tile);
    }

    @Test
    @Transactional("swag49.map")
    public void update_shouldUpdate() throws Exception{
		Map map = new Map();
        map.setUrl("test");
		map.setMaxUsers(5);

		map = mapDAO.create(map);

		Tile tile = new Tile(map,1 ,1);
		tile = tileDAO.create(tile);

		tile.setSpecial(ResourceType.GOLD);

		tile = tileDAO.update(tile);
    }
}
