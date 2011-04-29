package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Resource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="resourceDAO")
public class ResourceDao implements DataAccessObject<Resource> {

	@PersistenceContext
	private EntityManager em;

	public ResourceDao() {
	}

	public Resource get(Long id) {
		return em.find(Resource.class, id);
	}

	@Transactional
	public Resource create(Resource resource) {
		return em.merge(resource);
	}

	@Transactional
	public Resource update(Resource resource) {
		return em.merge(resource);
	}

	@Transactional
	public void delete(Resource resource) {
		resource = em.merge(resource);
		em.remove(resource);
	}

	
	public boolean contains(Resource resource)
	{
		return em.contains(resource);
	}
}
