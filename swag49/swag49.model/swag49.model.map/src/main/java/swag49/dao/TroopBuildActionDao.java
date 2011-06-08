package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopBuildAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "troopBuildActionDAO")
public class TroopBuildActionDao implements DataAccessObject<TroopBuildAction, Long> {

    public boolean contains(TroopBuildAction troopBuildAction) {
        return em.contains(troopBuildAction);
    }

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public TroopBuildAction create(TroopBuildAction troopBuildAction) {
        return em.merge(troopBuildAction);
    }

    @Transactional
    public void delete(TroopBuildAction troopBuildAction) {
        troopBuildAction = em.merge(troopBuildAction);
        em.remove(troopBuildAction);
    }

    public TroopBuildAction get(Long id) {
        return em.find(TroopBuildAction.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<TroopBuildAction> queryByExample(TroopBuildAction model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(TroopBuildAction.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional
    public TroopBuildAction update(TroopBuildAction troopBuildAction) {
        return em.merge(troopBuildAction);
    }
}
