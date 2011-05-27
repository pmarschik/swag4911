package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.BuildingType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="buildingTypeDAO")
public class BuildingTypeDao implements DataAccessObject<BuildingType> {

	@PersistenceContext
	private EntityManager em;

	public BuildingTypeDao() {
	}

	public boolean contains(BuildingType buildingType)
	{
		return em.contains(buildingType);
	}

	@Transactional
	public BuildingType create(BuildingType buildingType) {
		return em.merge(buildingType);
	}

	@Transactional
	public void delete(BuildingType buildingType) {
		buildingType = em.merge(buildingType);
		em.remove(buildingType);
	}

	public BuildingType get(Long id) {
		return em.find(BuildingType.class, id);
	}

	@Transactional
	public BuildingType update(BuildingType buildingType) {
		return em.merge(buildingType);
	}
}
