package swag49.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.model.MapLocation;

import javax.annotation.PostConstruct;

/**
 * @author michael
 */
@Component
@Scope(value = "singleton")
public class MapLocationHelper {

    @Autowired
    @Qualifier("mapLoactionDAO")
    private DataAccessObject<MapLocation, Long> mapLocationDAO;


    @PostConstruct
    @Transactional("swag49.user")
    public void init() {
        try {


            MapLocation mapLocation = new MapLocation();
            mapLocation.setUrl("http://localhost:8080/map");
            mapLocation.setMapName("Map - Venice");
            if (mapLocationDAO.queryByExample(mapLocation).isEmpty()) {
                mapLocationDAO.create(mapLocation);
            }

        } catch (Exception e) {
            // nothing to do
        }

        try {
            MapLocation mapLocation = new MapLocation();
            mapLocation.setUrl("http://localhost:8080/map1");
            mapLocation.setMapName("Map - Vienna");
            if (mapLocationDAO.queryByExample(mapLocation).isEmpty()) {
                mapLocationDAO.create(mapLocation);
            }
        } catch (Exception e) {
            // nothing to do
        }
    }
}
