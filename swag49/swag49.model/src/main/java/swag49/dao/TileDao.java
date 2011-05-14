package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Tile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="tileDAO")
public class TileDao implements DataAccessObject<Tile> {

	@PersistenceContext
	private EntityManager em;

	public TileDao() {
	}

	public boolean contains(Tile tile)
	{
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

	
	@Transactional
	public Tile update(Tile tile) {
		return em.merge(tile);
	}
}

