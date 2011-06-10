package swag49.web;

import org.slf4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.model.MapLocation;
import swag49.util.Log;

import javax.annotation.PostConstruct;

/**
 * @author michael
 */
@Component
@Scope("singleton")
public class MapLocationHelper implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    @Log
    private Logger log;

    @Autowired
    @Qualifier("mapLoactionDAO")
    private DataAccessObject<MapLocation, Long> mapLocationDAO;

    private AbstractApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        applicationContext.addApplicationListener(this);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (AbstractApplicationContext) applicationContext;
    }

    @Transactional("swag49.user")
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            MapLocation mapLocation = new MapLocation();
            mapLocation.setUrl("http://localhost:8080/map");
            mapLocation.setMapName("Map - Venice");
            if (mapLocationDAO.queryByExample(mapLocation).isEmpty()) {
                mapLocationDAO.create(mapLocation);
            }

        } catch (Exception e) {
            log.warn("failed to create map location", e);
        }

        try {
            MapLocation mapLocation = new MapLocation();
            mapLocation.setUrl("http://localhost:8080/map1");
            mapLocation.setMapName("Map - Vienna");
            if (mapLocationDAO.queryByExample(mapLocation).isEmpty()) {
                mapLocationDAO.create(mapLocation);
            }
        } catch (Exception e) {
            log.warn("failed to create map location", e);
        }
    }
}
