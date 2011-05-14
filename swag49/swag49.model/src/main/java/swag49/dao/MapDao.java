package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="mapDAO")
public class MapDao implements DataAccessObject<Map> {

	@PersistenceContext
	private EntityManager em;

	public MapDao() {
	}

	public boolean contains(Map map)
	{
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

	@Transactional
	public Map update(Map map) {
		return em.merge(map);
	}
}
