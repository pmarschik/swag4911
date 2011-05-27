package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.BuildingLevel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="buildingLevelDAO")
public class BuildingLevelDao implements DataAccessObject<BuildingLevel> {

	@PersistenceContext
	private EntityManager em;

	public BuildingLevelDao() {
	}

	public boolean contains(BuildingLevel buildingLevel)
	{
		return em.contains(buildingLevel);
	}

	@Transactional
	public BuildingLevel create(BuildingLevel buildingLevel) {
		return em.merge(buildingLevel);
	}

	@Transactional
	public void delete(BuildingLevel buildingLevel) {
		buildingLevel = em.merge(buildingLevel);
		em.remove(buildingLevel);
	}

	public BuildingLevel get(Long id) {
		return em.find(BuildingLevel.class, id);
	}

	@Transactional
	public BuildingLevel update(BuildingLevel buildingLevel) {
		return em.merge(buildingLevel);
	}
}

