package swag49.dao;

import org.springframework.stereotype.Repository;
import swag49.model.TroopUpgradeAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value = "troopUpgradeActionDAO")
public class TroopUpgradeActionDAO extends AbstractDataAccessObject<TroopUpgradeAction, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public TroopUpgradeActionDAO() {
        super(TroopUpgradeAction.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
