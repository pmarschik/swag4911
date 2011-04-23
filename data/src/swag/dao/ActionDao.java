package swag.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import swag.model.Action;

public class ActionDao implements IDao<Action> {

	@PersistenceContext
	private EntityManager em;

	public ActionDao() {
	}

	public Action get(Long id) {
		return em.find(Action.class, id);
	}

	public void create(Action Action) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(Action);

		tx.commit();

	}

	public void update(Action Action) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(Action);

		tx.commit();

	}

	public void delete(Action Action) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(Action);

		tx.commit();

	}

}
