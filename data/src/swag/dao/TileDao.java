package swag.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import swag.model.Tile;

public class TileDao implements IDao<Tile> {

	@PersistenceContext
	private EntityManager em;

	public TileDao(EntityManager em) {
		this.em = em;
	}

	public Tile get(Long id) {
		return em.find(Tile.class, id);
	}

	public void create(Tile Tile) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(Tile);

		tx.commit();

	}

	public void update(Tile Tile) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(Tile);

		tx.commit();

	}

	public void delete(Tile Tile) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(Tile);

		tx.commit();

	}
	
	public boolean contains(Tile tile)
	{
		return em.contains(tile);
	}

}
