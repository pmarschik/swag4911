package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Statistic;
import swag49.model.StatisticEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository(value = "statisticEntryDAO")
public class StatisticEntryDao implements DataAccessObject<StatisticEntry> {

	@PersistenceContext
	private EntityManager em;

	public StatisticEntryDao() {
	}

	public boolean contains(StatisticEntry statisticEntry) {
		return em.contains(statisticEntry);
	}

	@Transactional
	public StatisticEntry create(StatisticEntry statisticEntry) {
		return em.merge(statisticEntry);
	}

	@Transactional
	public void delete(StatisticEntry statisticEntry) {
		statisticEntry = em.merge(statisticEntry);
		em.remove(statisticEntry);
	}

	public StatisticEntry get(Object id) {
		return em.find(StatisticEntry.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<StatisticEntry> queryByExample(StatisticEntry model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(StatisticEntry.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public StatisticEntry update(StatisticEntry statisticEntry) {
		return em.merge(statisticEntry);
	}

}
