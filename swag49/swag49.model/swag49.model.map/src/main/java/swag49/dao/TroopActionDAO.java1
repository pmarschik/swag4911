package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("troopActionDAO")
public class TroopActionDAO implements DataAccessObject<TroopAction, Long> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(TroopAction troopAction) {
        return em.contains(troopAction);
    }

    @Transactional("swag49.map")
    public TroopAction create(TroopAction troopAction) {
        return em.merge(troopAction);
    }

    @Transactional("swag49.map")
    public void delete(TroopAction troopAction) {
        troopAction = em.merge(troopAction);
        em.remove(troopAction);
    }

    @Transactional("swag49.map")
    public TroopAction get(Long id) {
        return em.find(TroopAction.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<TroopAction> queryByExample(TroopAction model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(TroopAction.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public TroopAction update(TroopAction troopAction) {
        return em.merge(troopAction);
    }

}
