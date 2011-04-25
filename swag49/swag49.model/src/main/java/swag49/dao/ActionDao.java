package swag49.dao;

import swag49.model.Action;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

public class ActionDao implements DataAccessObject<Action> {

	@PersistenceContext
	private EntityManager em;

	public ActionDao(EntityManager em) {
		this.em = em;
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

	public boolean contains(Long id)
	{
		return em.contains(id);
	}
}
