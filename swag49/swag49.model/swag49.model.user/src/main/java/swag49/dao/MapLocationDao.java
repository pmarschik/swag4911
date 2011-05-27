package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.MapLocation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	public MapLocation get(Long id) {
		return em.find(MapLocation.class, id);
	}

	@Transactional
	public MapLocation update(MapLocation mapLocation) {
		return em.merge(mapLocation);
	}
}
