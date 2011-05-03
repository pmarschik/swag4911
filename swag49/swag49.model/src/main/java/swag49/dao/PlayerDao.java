package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="playerDAO")
public class PlayerDao implements DataAccessObject<Player> {

	@PersistenceContext
	private EntityManager em;

	public PlayerDao() {
	}

	public boolean contains(Player player)
	{
		return em.contains(player);
	}

	@Transactional
	public Player create(Player player) {
		return em.merge(player);
	}

	@Transactional
	public void delete(Player player) {
		player = em.merge(player);
		em.remove(player);
	}

	public Player get(Long id) {
		return em.find(Player.class, id);
	}

	
	@Transactional
	public Player update(Player player) {
		return em.merge(player);
	}
}
