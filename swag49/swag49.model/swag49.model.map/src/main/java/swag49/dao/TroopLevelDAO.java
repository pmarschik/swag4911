package swag49.dao;

import org.springframework.stereotype.Repository;
import swag49.model.TroopLevel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value = "troopLevelDAO")
public class TroopLevelDAO extends AbstractDataAccessObject<TroopLevel, TroopLevel.Id> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public TroopLevelDAO() {
        super(TroopLevel.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
