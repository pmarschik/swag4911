package swag.dao;

import swag.model.Resource;

public class ResourceDao implements DataAccessObject<Resource> {

	private javax.persistence.EntityManager em;

	public ResourceDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public Resource get(Long id) {
		return em.find(Resource.class, id);
	}

	public Resource create(Resource resource) {
		Resource temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(resource);

		tx.commit();

		return temp;
	}

	public Resource update(Resource resource) {
		Resource temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(resource);

		tx.commit();

		return temp;
	}

	public void delete(Resource resource) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(resource);

		tx.commit();

	}

	public boolean contains(Resource resource) {
		return em.contains(resource);
	}

}
