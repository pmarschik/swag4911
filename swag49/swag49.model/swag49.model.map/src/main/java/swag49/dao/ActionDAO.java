package swag49.dao;

import org.springframework.stereotype.Repository;
import swag49.model.Action;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("actionDAO")
public class ActionDAO extends AbstractDataAccessObject<Action, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public ActionDAO() {
        super(Action.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
