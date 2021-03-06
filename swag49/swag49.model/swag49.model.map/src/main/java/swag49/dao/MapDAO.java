package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("mapDAO")
@Transactional("swag49.map")
public class MapDAO extends AbstractDataAccessObject<Map, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public MapDAO() {
        super(Map.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
