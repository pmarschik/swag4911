package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopUpgradeAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("troopUpgradeActionDAO")
@Transactional("swag49.map")
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
