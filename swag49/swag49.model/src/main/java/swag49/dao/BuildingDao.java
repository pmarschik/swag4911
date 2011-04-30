package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Building;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="buildingDAO")
public class BuildingDao implements DataAccessObject<Building> {

	@PersistenceContext
	private EntityManager em;

	public BuildingDao() {
	}

	public Building get(Long id) {
		return em.find(Building.class, id);
	}

	@Transactional
	public Building create(Building building) {
		return em.merge(building);
	}

	@Transactional
	public Building update(Building building) {
		return em.merge(building);
	}

	@Transactional
	public void delete(Building building) {
		building = em.merge(building);
		em.remove(building);
	}

	public boolean contains(Building building)
	{
		return em.contains(building);
	}
}