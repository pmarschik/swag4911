package swag49.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import swag49.model.BuildingLevel;

@Repository(value = "buildingLevelDAO")
public class BuildingLevelDao implements DataAccessObject<BuildingLevel> {

	@PersistenceContext
	private EntityManager em;

	public BuildingLevelDao() {
	}

	public boolean contains(BuildingLevel buildingLevel) {
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

	public BuildingLevel get(Object id) {
		return em.find(BuildingLevel.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<BuildingLevel> queryByExample(BuildingLevel model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(BuildingLevel.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public BuildingLevel update(BuildingLevel buildingLevel) {
		return em.merge(buildingLevel);
	}

}
