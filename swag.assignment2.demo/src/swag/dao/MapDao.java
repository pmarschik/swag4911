package swag.dao;

import swag.model.Map;

public class MapDao implements DataAccessObject<Map> {

	private javax.persistence.EntityManager em;

	public MapDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public Map get(Long id) {
		return em.find(Map.class, id);
	}

	public Map create(Map map) {
		Map temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(map);

		tx.commit();

		return temp;
	}

	public Map update(Map map) {
		Map temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(map);

		tx.commit();

		return temp;
	}

	public void delete(Map map) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(map);

		tx.commit();

	}

	public boolean contains(Map map) {
		return em.contains(map);
	}

}
