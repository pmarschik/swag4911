package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Troop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//annotate with @Repository because it is a DAO, other components should be annotated with @Component
@Repository("troopDAO")
@Transactional("swag49.map")
public class TroopDAO extends AbstractDataAccessObject<Troop, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public TroopDAO() {
        super(Troop.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
