package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopLevel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "troopLevelDAO")
public class TroopLevelDao implements DataAccessObject<TroopLevel> {

	@PersistenceContext
	private EntityManager em;

	public TroopLevelDao() {
	}

	public boolean contains(TroopLevel troopLevel) {
		return em.contains(troopLevel);
	}

	@Transactional
	public TroopLevel create(TroopLevel troopLevel) {
		return em.merge(troopLevel);
	}

	@Transactional
	public void delete(TroopLevel troopLevel) {
		troopLevel = em.merge(troopLevel);
		em.remove(troopLevel);
	}

	public TroopLevel get(Object id) {
		return em.find(TroopLevel.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<TroopLevel> queryByExample(TroopLevel model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(TroopLevel.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public TroopLevel update(TroopLevel troopLevel) {
		return em.merge(troopLevel);
	}

}
