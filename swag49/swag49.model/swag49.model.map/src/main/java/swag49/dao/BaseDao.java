package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("baseDAO")
public class BaseDAO implements DataAccessObject<Base, Long> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(Base base) {
        return em.contains(base);
    }

    @Transactional("swag49.map")
    public Base create(Base base) {
        return em.merge(base);

    }

    @Transactional("swag49.map")
    public void delete(Base base) {
        base = em.merge(base);
        em.remove(base);

    }

    @Transactional("swag49.map")
    public Base get(Long id) {
        return em.find(Base.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<Base> queryByExample(Base model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Base.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public Base update(Base base) {
        return em.merge(base);
    }

}
