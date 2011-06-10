package swag49.dao;

import org.springframework.stereotype.Repository;
import swag49.model.Tile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("tileDAO")
public class TileDAO extends AbstractDataAccessObject<Tile, Tile.Id> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public TileDAO() {
        super(Tile.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
