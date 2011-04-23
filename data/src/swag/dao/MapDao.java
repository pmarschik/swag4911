package swag.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import swag.model.Map;

public class MapDao implements IDao<Map> {

	@PersistenceContext
	private EntityManager em;

	public MapDao() {
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

}
