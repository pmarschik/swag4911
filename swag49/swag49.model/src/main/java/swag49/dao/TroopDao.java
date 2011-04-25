package swag49.dao;

import swag49.model.Troop;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

public class TroopDao implements DataAccessObject<Troop> {

	@PersistenceContext
	private EntityManager em;

	public TroopDao(EntityManager em) {
		this.em = em;
	}

	public Troop get(Long id) {
		return em.find(Troop.class, id);
	}

	public void create(Troop Troop) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(Troop);

		tx.commit();

	}

	public void update(Troop Troop) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(Troop);

		tx.commit();

	}

	public void delete(Troop Troop) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(Troop);

		tx.commit();

	}

	public boolean contains(Long id)
	{
		return em.contains(id);
	}
}
