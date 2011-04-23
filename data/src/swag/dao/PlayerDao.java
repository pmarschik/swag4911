package swag.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import swag.model.Player;

public class PlayerDao implements IDao<Player> {

	@PersistenceContext
	private EntityManager em;

	public PlayerDao() {
	}

	public Player get(Long id) {
		return em.find(Player.class, id);
	}

	public void create(Player Player) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(Player);

		tx.commit();

	}

	public void update(Player Player) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(Player);

		tx.commit();

	}

	public void delete(Player Player) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(Player);

		tx.commit();

	}

}
