package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.StatisticEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("statisticEntryDAO")
public class StatisticEntryDAO implements DataAccessObject<StatisticEntry, StatisticEntry.Id> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(StatisticEntry statisticEntry) {
        return em.contains(statisticEntry);
    }

    @Transactional("swag49.map")
    public StatisticEntry create(StatisticEntry statisticEntry) {
        return em.merge(statisticEntry);
    }

    @Transactional("swag49.map")
    public void delete(StatisticEntry statisticEntry) {
        statisticEntry = em.merge(statisticEntry);
        em.remove(statisticEntry);
    }

    @Transactional("swag49.map")
    public StatisticEntry get(StatisticEntry.Id id) {
        return em.find(StatisticEntry.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<StatisticEntry> queryByExample(StatisticEntry model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(StatisticEntry.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public StatisticEntry update(StatisticEntry statisticEntry) {
        return em.merge(statisticEntry);
    }

}
