package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Tile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("tileDAO")
public class TileDAO implements DataAccessObject<Tile, Tile.Id> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(Tile tile) {
        return em.contains(tile);
    }

    @Transactional("swag49.map")
    public Tile create(Tile tile) {
        return em.merge(tile);
    }

    @Transactional("swag49.map")
    public void delete(Tile tile) {
        tile = em.merge(tile);
        em.remove(tile);
    }

    @Transactional("swag49.map")
    public Tile get(Tile.Id id) {
        return em.find(Tile.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<Tile> queryByExample(Tile model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Tile.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public Tile update(Tile tile) {
        return em.merge(tile);
    }

}
