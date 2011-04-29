package swag49.dao;

import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import swag49.model.Square;

@Repository(value = "squareDAO")
public class SquareDao implements DataAccessObject<Square> {

	@PersistenceContext
	private javax.persistence.EntityManager em;

	public SquareDao() {
	}

	public SquareDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public Square get(Long id) {
		return em.find(Square.class, id);
	}

	public Square create(Square square) {
		em.merge(square);
		return square;
	}

	public Square update(Square square) {
		em.merge(square);
		return square;
	}

	public void delete(Square square) {
		em.remove(square);

	}

	public boolean contains(Square square) {
		return em.contains(square);
	}

}
