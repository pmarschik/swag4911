package swag.dao;

import swag.model.Troop;

public class TroopDao implements DataAccessObject<Troop> {

	private javax.persistence.EntityManager em;

	public TroopDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public Troop get(Long id) {
		return em.find(Troop.class, id);
	}

	public Troop create(Troop troop) {
		Troop temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(troop);

		tx.commit();

		return temp;
	}

	public Troop update(Troop troop) {
		Troop temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(troop);

		tx.commit();

		return temp;
	}

	public void delete(Troop troop) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(troop);

		tx.commit();

	}

	public boolean contains(Troop troop) {
		return em.contains(troop);
	}

}
