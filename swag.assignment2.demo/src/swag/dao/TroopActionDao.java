package swag.dao;

import swag.model.TroopAction;

public class TroopActionDao implements DataAccessObject<TroopAction> {

	private javax.persistence.EntityManager em;

	public TroopActionDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public TroopAction get(Long id) {
		return em.find(TroopAction.class, id);
	}

	public TroopAction create(TroopAction troopaction) {
		TroopAction temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(troopaction);

		tx.commit();

		return temp;
	}

	public TroopAction update(TroopAction troopaction) {
		TroopAction temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(troopaction);

		tx.commit();

		return temp;
	}

	public void delete(TroopAction troopaction) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(troopaction);

		tx.commit();

	}

	public boolean contains(TroopAction troopaction) {
		return em.contains(troopaction);
	}

}
