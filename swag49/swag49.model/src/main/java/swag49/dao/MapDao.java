package swag49.dao;

import swag49.model.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

public class MapDao implements DataAccessObject<Map> {

	@PersistenceContext
	private EntityManager em;

	public MapDao(EntityManager em) {
		this.em = em;
	}

	public Map get(Long id) {
		return em.find(Map.class, id);
	}

	public void create(Map Map) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(Map);

		tx.commit();

	}

	public void update(Map Map) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(Map);

		tx.commit();

	}

	public void delete(Map Map) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(Map);

		tx.commit();

	}

	public boolean contains(Long id)
	{
		return em.contains(id);
	}
}
