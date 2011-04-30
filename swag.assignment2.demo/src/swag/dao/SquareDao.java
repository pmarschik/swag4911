package swag.dao;

import swag.model.Square;

public class SquareDao implements DataAccessObject<Square> {

	private javax.persistence.EntityManager em;

	public SquareDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public Square get(Long id) {
		return em.find(Square.class, id);
	}

	public Square create(Square square) {
		Square temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(square);

		tx.commit();

		return temp;
	}

	public Square update(Square square) {
		Square temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(square);

		tx.commit();

		return temp;
	}

	public void delete(Square square) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(square);

		tx.commit();

	}

	public boolean contains(Square square) {
		return em.contains(square);
	}

}
