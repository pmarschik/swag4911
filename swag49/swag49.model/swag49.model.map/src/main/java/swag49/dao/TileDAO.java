package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Tile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("tileDAO")
@Transactional("swag49.map")
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
