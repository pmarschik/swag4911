package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Action;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("actionDAO")
public class ActionDAO implements DataAccessObject<Action, Long> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(Action action) {
        return em.contains(action);
    }

    @Transactional("swag49.map")
    public Action create(Action action) {
        return em.merge(action);
    }

    @Transactional("swag49.map")
    public void delete(Action action) {
        action = em.merge(action);
        em.remove(action);
    }

    @Transactional("swag49.map")
    public Action get(Long id) {
        return em.find(Action.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<Action> queryByExample(Action model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Action.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public Action update(Action action) {
        return em.merge(action);
    }

}
