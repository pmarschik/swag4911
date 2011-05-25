package swag49.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import swag49.model.BuildingType;

@Repository(value = "buildingTypeDAO")
public class BuildingTypeDao implements DataAccessObject<BuildingType> {

	@PersistenceContext
	private EntityManager em;

	public BuildingTypeDao() {
	}

	public boolean contains(BuildingType buildingType) {
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

	public BuildingType get(Object id) {
		return em.find(BuildingType.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<BuildingType> queryByExample(BuildingType model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(BuildingType.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public BuildingType update(BuildingType buildingType) {
		return em.merge(buildingType);
	}

}
