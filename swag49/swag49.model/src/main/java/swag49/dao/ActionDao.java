package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Action;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="actionDAO")
public class ActionDao implements DataAccessObject<Action> {

	@PersistenceContext
	private EntityManager em;

	public ActionDao() {
	}

	public boolean contains(Action action)
	{
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

	public Action get(Long id) {
		return em.find(Action.class, id);
	}

	@Transactional
	public Action update(Action action) {
		return em.merge(action);
	}
}
