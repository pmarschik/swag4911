package swag49.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import swag49.model.Player;

@Repository(value = "playerDAO")
public class PlayerDao implements DataAccessObject<Player> {

	@PersistenceContext
	private EntityManager em;

	public PlayerDao() {
	}

	public boolean contains(Player player) {
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

	public Player get(Object id) {
		return em.find(Player.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Player> queryByExample(Player model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Player.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public Player update(Player player) {
		return em.merge(player);
	}
}
