package swag49.dao;

import org.springframework.stereotype.Repository;
import swag49.model.BuildAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("buildActionDAO")
public class BuildActionDAO extends AbstractDataAccessObject<BuildAction, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public BuildActionDAO() {
        super(BuildAction.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
