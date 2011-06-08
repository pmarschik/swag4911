package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopUpgradeAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "troopUpgradeActionDAO")
public class TroopUpgradeActionDao implements DataAccessObject<TroopUpgradeAction> {

    public boolean contains(TroopUpgradeAction troopUpgradeAction) {
        return em.contains(troopUpgradeAction);
    }

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public TroopUpgradeAction create(TroopUpgradeAction troopUpgradeAction) {
        return em.merge(troopUpgradeAction);
    }

    @Transactional
    public void delete(TroopUpgradeAction troopUpgradeAction) {
        troopUpgradeAction = em.merge(troopUpgradeAction);
        em.remove(troopUpgradeAction);
    }

    public TroopUpgradeAction get(Object id) {
        return em.find(TroopUpgradeAction.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<TroopUpgradeAction> queryByExample(TroopUpgradeAction model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(TroopUpgradeAction.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional
    public TroopUpgradeAction update(TroopUpgradeAction troopUpgradeAction) {
        return em.merge(troopUpgradeAction);
    }
}
