package swag49.dao;

import org.springframework.stereotype.Repository;
import swag49.model.Base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("baseDAO")
public class BaseDAO extends AbstractDataAccessObject<Base, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public BaseDAO() {
        super(Base.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
