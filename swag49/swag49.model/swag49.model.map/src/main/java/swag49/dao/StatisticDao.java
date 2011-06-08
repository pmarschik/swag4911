package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Statistic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("statisticDAO")
public class StatisticDAO implements DataAccessObject<Statistic, Long> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(Statistic statistic) {
        return em.contains(statistic);
    }

    @Transactional("swag49.map")
    public Statistic create(Statistic statistic) {
        return em.merge(statistic);
    }

    @Transactional("swag49.map")
    public void delete(Statistic statistic) {
        statistic = em.merge(statistic);
        em.remove(statistic);
    }

    @Transactional("swag49.map")
    public Statistic get(Long id) {
        return em.find(Statistic.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<Statistic> queryByExample(Statistic model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Statistic.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public Statistic update(Statistic statistic) {
        return em.merge(statistic);
    }

}
