package swag49.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import swag49.model.TroopAction;

@Repository(value = "troopActionDAO")
public class TroopActionDao implements DataAccessObject<TroopAction> {

	@PersistenceContext
	private EntityManager em;

	public TroopActionDao() {
	}

	public boolean contains(TroopAction troopAction) {
		return em.contains(troopAction);
	}

	@Transactional
	public TroopAction create(TroopAction troopAction) {
		return em.merge(troopAction);
	}

	@Transactional
	public void delete(TroopAction troopAction) {
		troopAction = em.merge(troopAction);
		em.remove(troopAction);
	}

	public TroopAction get(Object id) {
		return em.find(TroopAction.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<TroopAction> queryByExample(TroopAction model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(TroopAction.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public TroopAction update(TroopAction troopAction) {
		return em.merge(troopAction);
	}

}
