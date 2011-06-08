package swag49.dao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.MapLocation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("mapLoactionDAO")
public class MapLocationDAO implements DataAccessObject<MapLocation, Long> {

	@PersistenceContext(unitName = "swag49.user")
	private EntityManager em;

	public MapLocationDAO() {
	}

    @Transactional("swag49.user")
	public boolean contains(MapLocation mapLocation)
	{
		return em.contains(mapLocation);
	}

	@Transactional("swag49.user")
	public MapLocation create(MapLocation mapLocation) {
		return em.merge(mapLocation);
	}

	@Transactional("swag49.user")
	public void delete(MapLocation mapLocation) {
		mapLocation = em.merge(mapLocation);
		em.remove(mapLocation);
	}

    @SuppressWarnings({"unchecked"})
    @Transactional("swag49.user")
    public List<MapLocation> queryByExample(MapLocation model) {
        Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(MapLocation.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
    }

    @Transactional("swag49.user")
    public MapLocation get(Long id) {
		return em.find(MapLocation.class, id);
	}

	@Transactional("swag49.user")
	public MapLocation update(MapLocation mapLocation) {
		return em.merge(mapLocation);
	}
}
