package swag49.dao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.MapLocation;
import swag49.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository(value="mapLoactionDAO")
public class MapLocationDao implements DataAccessObject<MapLocation> {

	@PersistenceContext
	private EntityManager em;

	public MapLocationDao() {
	}

	public boolean contains(MapLocation mapLocation)
	{
		return em.contains(mapLocation);
	}

	@Transactional
	public MapLocation create(MapLocation mapLocation) {
		return em.merge(mapLocation);
	}

	@Transactional
	public void delete(MapLocation mapLocation) {
		mapLocation = em.merge(mapLocation);
		em.remove(mapLocation);
	}

    public MapLocation get(Object id) {
        return em.find(MapLocation.class, id);
    }

    public Collection<MapLocation> queryByExample(MapLocation model) {
        Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(MapLocation.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
    }

    public MapLocation get(Long id) {
		return em.find(MapLocation.class, id);
	}

	@Transactional
	public MapLocation update(MapLocation mapLocation) {
		return em.merge(mapLocation);
	}
}