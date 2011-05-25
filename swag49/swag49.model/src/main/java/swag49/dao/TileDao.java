package swag49.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import swag49.model.Tile;

@Repository(value = "tileDAO")
public class TileDao implements DataAccessObject<Tile> {

	@PersistenceContext
	private EntityManager em;

	public TileDao() {
	}

	public boolean contains(Tile tile) {
		return em.contains(tile);
	}

	@Transactional
	public Tile create(Tile tile) {
		return em.merge(tile);
	}

	@Transactional
	public void delete(Tile tile) {
		tile = em.merge(tile);
		em.remove(tile);
	}

	public Tile get(Object id) {
		return em.find(Tile.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Tile> queryByExample(Tile model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Tile.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public Tile update(Tile tile) {
		return em.merge(tile);
	}

}
