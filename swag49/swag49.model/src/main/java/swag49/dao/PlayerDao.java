package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Repository(value="playerDAO")
public class PlayerDao implements DataAccessObject<Player> {

	@PersistenceContext
	private EntityManager em;

	public PlayerDao() {
	}

	public Player get(Long id) {
		return em.find(Player.class, id);
	}

	@Transactional
	public Player create(Player Player) {
		return em.merge(Player);
	}

	@Transactional
	public Player update(Player Player) {
		return em.merge(Player);
	}

	@Transactional
	public void delete(Player Player) {
		em.remove(Player);
	}

	
	public boolean contains(Long id)
	{
		return em.contains(id);
	}
}
