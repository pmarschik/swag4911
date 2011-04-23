package swag.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import swag.model.Base;

public class BaseDao implements IDao<Base> {

	@PersistenceContext
	private EntityManager em;

	public BaseDao(EntityManager em) {
		this.em = em;
	}

	public Base get(Long id) {
		return em.find(Base.class, id);
	}

	public void create(Base Base) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(Base);

		tx.commit();

	}

	public void update(Base Base) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(Base);

		tx.commit();

	}

	public void delete(Base Base) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(Base);

		tx.commit();

	}

	public boolean contains(Long id)
	{
		return em.contains(id);
	}
}
