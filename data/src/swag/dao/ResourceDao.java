package swag.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import swag.model.Resource;

public class ResourceDao implements IDao<Resource> {

	@PersistenceContext
	private EntityManager em;

	public ResourceDao() {
	}

	public Resource get(Long id) {
		return em.find(Resource.class, id);
	}

	public void create(Resource Resource) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(Resource);

		tx.commit();

	}

	public void update(Resource Resource) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(Resource);

		tx.commit();

	}

	public void delete(Resource Resource) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(Resource);

		tx.commit();

	}

}
