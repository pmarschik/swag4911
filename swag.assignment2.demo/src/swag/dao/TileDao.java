package swag.dao;

import swag.model.Tile;

public class TileDao implements DataAccessObject<Tile> {

	private javax.persistence.EntityManager em;

	public TileDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public Tile get(Long id) {
		return em.find(Tile.class, id);
	}

	public Tile create(Tile tile) {
		Tile temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(tile);

		tx.commit();

		return temp;
	}

	public Tile update(Tile tile) {
		Tile temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(tile);

		tx.commit();

		return temp;
	}

	public void delete(Tile tile) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(tile);

		tx.commit();

	}

	public boolean contains(Tile tile) {
		return em.contains(tile);
	}

}
