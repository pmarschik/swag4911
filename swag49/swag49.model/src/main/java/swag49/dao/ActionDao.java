package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Action;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Repository(value="actionDAO")
public class ActionDao implements DataAccessObject<Action> {

	@PersistenceContext
	private EntityManager em;

	public ActionDao() {
	}

	public Action get(Long id) {
		return em.find(Action.class, id);
	}

	@Transactional
	public Action create(Action Action) {
		return em.merge(Action);
	}

	@Transactional
	public Action update(Action Action) {
		return em.merge(Action);
	}

	@Transactional
	public void delete(Action Action) {
		em.remove(Action);
	}

	public boolean contains(Long id)
	{
		return em.contains(id);
	}
}
