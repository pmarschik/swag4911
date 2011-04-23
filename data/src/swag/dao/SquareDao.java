package swag.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import swag.model.Square;

public class SquareDao implements IDao<Square> {

	@PersistenceContext
	private EntityManager em;

	public SquareDao() {
	}

	public Square get(Long id) {
		return em.find(Square.class, id);
	}

	public void create(Square Square) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(Square);

		tx.commit();

	}

	public void update(Square Square) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(Square);

		tx.commit();

	}

	public void delete(Square Square) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(Square);

		tx.commit();

	}

}
