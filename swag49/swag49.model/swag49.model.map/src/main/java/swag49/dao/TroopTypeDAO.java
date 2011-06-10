package swag49.dao;

import org.springframework.stereotype.Repository;
import swag49.model.TroopType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value = "troopTypeDAO")
public class TroopTypeDAO extends AbstractDataAccessObject<TroopType, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public TroopTypeDAO() {
        super(TroopType.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
