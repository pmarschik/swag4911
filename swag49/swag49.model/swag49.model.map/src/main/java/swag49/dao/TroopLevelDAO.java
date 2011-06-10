package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopLevel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("troopLevelDAO")
@Transactional("swag49.map")
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
