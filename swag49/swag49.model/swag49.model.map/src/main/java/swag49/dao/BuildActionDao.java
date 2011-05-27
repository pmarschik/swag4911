package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.BuildAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="buildActionDAO")
public class BuildActionDao implements DataAccessObject<BuildAction> {

	@PersistenceContext
	private EntityManager em;

	public BuildActionDao() {
	}

	public boolean contains(BuildAction buildAction)
	{
		return em.contains(buildAction);
	}

	@Transactional
	public BuildAction create(BuildAction buildAction) {
		return em.merge(buildAction);
	}

	@Transactional
	public void delete(BuildAction buildAction) {
		buildAction = em.merge(buildAction);
		em.remove(buildAction);
	}

	public BuildAction get(Long id) {
		return em.find(BuildAction.class, id);
	}

	@Transactional
	public BuildAction update(BuildAction buildAction) {
		return em.merge(buildAction);
	}
}

