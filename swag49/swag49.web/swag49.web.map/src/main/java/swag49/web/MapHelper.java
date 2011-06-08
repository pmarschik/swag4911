package swag49.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.model.Map;
import swag49.model.ResourceType;
import swag49.model.Tile;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @author michael
 */
@Component
@Scope(value = "singleton")
public class MapHelper {

    @Autowired
    @Qualifier("mapDAO")
    private DataAccessObject<Map> mapDAO;

    @Autowired
    @Qualifier("tileDAO")
    private DataAccessObject<Tile> tileDAO;


    @PostConstruct
    @Transactional
    public void init() {
        try {
            //create map
            Map map = new Map();
            map.setUrl("http://localhost:8080/map");

            if (mapDAO.queryByExample(map).isEmpty()) {
                map.setMaxUsers(100);
                Set<Tile> tiles = new HashSet<Tile>();
                map = mapDAO.create(map);
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        Tile tile = new Tile(map, i, j);
                        tile.setSpecial(ResourceType.NONE);
                        tile.setBase(null);
                        tile = tileDAO.create(tile);

                        tiles.add(tile);
                    }
                }
                map.setConsistsOf(tiles);

                mapDAO.update(map);
            }

        } catch (Exception e) {
            // nothing to do
        }

        try {
            //create map
            Map map = new Map();
            map.setUrl("http://localhost:8080/map1");
            if (mapDAO.queryByExample(map).isEmpty()) {
                map.setMaxUsers(100);
                Set<Tile> tiles = new HashSet<Tile>();
                map = mapDAO.create(map);
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        Tile tile = new Tile(map, i, j);
                        tile.setSpecial(ResourceType.NONE);
                        tile.setBase(null);
                        tile = tileDAO.create(tile);

                        tiles.add(tile);
                    }
                }
                map.setConsistsOf(tiles);

                mapDAO.update(map);
            }
        } catch (Exception e) {
            // nothing to do
        }
    }
}
