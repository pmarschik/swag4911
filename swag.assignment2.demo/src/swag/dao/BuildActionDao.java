package swag.dao;

import swag.model.BuildAction;

public class BuildActionDao implements DataAccessObject<BuildAction> {

	private javax.persistence.EntityManager em;

	public BuildActionDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public BuildAction get(Long id) {
		return em.find(BuildAction.class, id);
	}

	public BuildAction create(BuildAction buildaction) {
		BuildAction temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(buildaction);

		tx.commit();

		return temp;
	}

	public BuildAction update(BuildAction buildaction) {
		BuildAction temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(buildaction);

		tx.commit();

		return temp;
	}

	public void delete(BuildAction buildaction) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(buildaction);

		tx.commit();

	}

	public boolean contains(BuildAction buildaction) {
		return em.contains(buildaction);
	}

}
