package swag49.dao;

import org.springframework.stereotype.Repository;
import swag49.model.TroopBuildAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value = "troopBuildActionDAO")
public class TroopBuildActionDAO extends AbstractDataAccessObject<TroopBuildAction, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public TroopBuildActionDAO() {
        super(TroopBuildAction.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
