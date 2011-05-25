package swag49.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import swag49.model.Map;

@Repository(value = "mapDAO")
public class MapDao implements DataAccessObject<Map> {

	@PersistenceContext
	private EntityManager em;

	public MapDao() {
	}

	public boolean contains(Map map) {
		return em.contains(map);
	}

	@Transactional
	public Map create(Map map) {
		return em.merge(map);
	}

	@Transactional
	public void delete(Map map) {
		map = em.merge(map);
		em.remove(map);
	}

	public Map get(Object id) {
		return em.find(Map.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Map> queryByExample(Map model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Map.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public Map update(Map map) {
		return em.merge(map);
	}

}
