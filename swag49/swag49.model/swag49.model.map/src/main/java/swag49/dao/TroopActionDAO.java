package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("troopActionDAO")
@Transactional("swag49.map")
public class TroopActionDAO extends AbstractDataAccessObject<TroopAction, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public TroopActionDAO() {
        super(TroopAction.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
