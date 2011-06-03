package swag49.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import swag49.model.MapLocation;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author michael
 */
@Component
@Scope(value = "singleton")
public class HelperComponent {

    private List<MapLocation> mapLocations = new ArrayList<MapLocation>();

    @PostConstruct
    public void init() {
        MapLocation mapLocation = new MapLocation();
        mapLocation.setUrl("http://localhost:8080/map");
        mapLocation.setId(1L);
        mapLocations.add(mapLocation);

        mapLocation = new MapLocation();
        mapLocation.setUrl("http://localhost:8080/map1");
        mapLocation.setId(2L);
        mapLocations.add(mapLocation);
    }

    public List<MapLocation> getMapLocations() {
        return mapLocations;
    }

    public void setMapLocations(List<MapLocation> mapLocations) {
        this.mapLocations = mapLocations;
    }
}
