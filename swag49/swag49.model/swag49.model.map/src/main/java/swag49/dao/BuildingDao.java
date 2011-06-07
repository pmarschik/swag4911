package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Building;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "buildingDAO")
public class BuildingDao implements DataAccessObject<Building> {

	@PersistenceContext
	private EntityManager em;

	public BuildingDao() {
	}

	public boolean contains(Building building) {
		return em.contains(building);
	}

	@Transactional
	public Building create(Building building) {
		return em.merge(building);
	}

	@Transactional
	public void delete(Building building) {
		building = em.merge(building);
		em.remove(building);
	}

	public Building get(Object id) {
		return em.find(Building.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Building> queryByExample(Building model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Building.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public Building update(Building building) {
		return em.merge(building);
	}

}