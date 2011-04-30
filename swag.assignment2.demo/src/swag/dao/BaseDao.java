package swag.dao;

import swag.model.Base;

public class BaseDao implements DataAccessObject<Base> {

	private javax.persistence.EntityManager em;

	public BaseDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public Base get(Long id) {
		return em.find(Base.class, id);
	}

	public Base create(Base base) {
		Base temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(base);

		tx.commit();

		return temp;
	}

	public Base update(Base base) {
		Base temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(base);

		tx.commit();

		return temp;
	}

	public void delete(Base base) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(base);

		tx.commit();

	}

	public boolean contains(Base base) {
		return em.contains(base);
	}

}
