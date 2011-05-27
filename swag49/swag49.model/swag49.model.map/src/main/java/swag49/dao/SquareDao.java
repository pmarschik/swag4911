package swag49.dao;

import java.util.Collection;

import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

	public boolean contains(Square square) {
		return em.contains(square);
	}

	@Transactional
	public Square create(Square square) {
		em.merge(square);
		return square;
	}

	@Transactional
	public void delete(Square square) {
		square = em.merge(square);
		em.remove(square);

	}

	public Square get(Object id) {
		return em.find(Square.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Square> queryByExample(Square model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Square.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public Square update(Square square) {
		em.merge(square);
		return square;
	}

}
