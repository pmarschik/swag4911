package swag.dao;

import swag.model.Player;

public class PlayerDao implements DataAccessObject<Player> {

	private javax.persistence.EntityManager em;

	public PlayerDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public Player get(Long id) {
		return em.find(Player.class, id);
	}

	public Player create(Player player) {
		Player temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(player);

		tx.commit();

		return temp;
	}

	public Player update(Player player) {
		Player temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(player);

		tx.commit();

		return temp;
	}

	public void delete(Player player) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(player);

		tx.commit();

	}

	public boolean contains(Player player) {
		return em.contains(player);
	}

}
