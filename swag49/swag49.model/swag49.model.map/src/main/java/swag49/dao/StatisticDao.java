package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Statistic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository(value = "statisticDAO")
public class StatisticDao implements DataAccessObject<Statistic> {

	@PersistenceContext
	private EntityManager em;

	public StatisticDao() {
	}

	public boolean contains(Statistic statistic) {
		return em.contains(statistic);
	}

	@Transactional
	public Statistic create(Statistic statistic) {
		return em.merge(statistic);
	}

	@Transactional
	public void delete(Statistic statistic) {
		statistic = em.merge(statistic);
		em.remove(statistic);
	}

	public Statistic get(Object id) {
		return em.find(Statistic.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Statistic> queryByExample(Statistic model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Statistic.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public Statistic update(Statistic statistic) {
		return em.merge(statistic);
	}

}
