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

@Repository(value = "actionDAO")
public class ActionDao implements DataAccessObject<Action> {

	@PersistenceContext
	private EntityManager em;

	public ActionDao() {
	}

	public boolean contains(Action action) {
		return em.contains(action);
	}

	@Transactional
	public Action create(Action action) {
		return em.merge(action);
	}

	@Transactional
	public void delete(Action action) {
		action = em.merge(action);
		em.remove(action);
	}

	public Action get(Object id) {
		return em.find(Action.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Action> queryByExample(Action model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Action.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public Action update(Action action) {
		return em.merge(action);
	}

}
