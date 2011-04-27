package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Repository(value="mapDAO")
public class MapDao implements DataAccessObject<Map> {

	@PersistenceContext
	private EntityManager em;

	public MapDao() {
	}

	public Map get(Long id) {
		return em.find(Map.class, id);
	}

	@Transactional
	public Map create(Map Map) {
		return em.merge(Map);
	}

	@Transactional
	public Map update(Map Map) {
		return em.merge(Map);
	}

	@Transactional
	public void delete(Map Map) {
		em.remove(Map);
	}

	public boolean contains(Long id)
	{
		return em.contains(id);
	}
}
