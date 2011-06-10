package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.MapLocation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("mapLoactionDAO")
@Transactional("swag49.user")
public class MapLocationDAO extends AbstractDataAccessObject<MapLocation, Long> {
    @PersistenceContext(unitName = "swag49.user")
    private EntityManager em;

    public MapLocationDAO() {
        super(MapLocation.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
