package swag49.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import swag49.model.BuildAction;

@Repository(value = "buildActionDAO")
public class BuildActionDao implements DataAccessObject<BuildAction> {

	@PersistenceContext
	private EntityManager em;

	public BuildActionDao() {
	}

	public boolean contains(BuildAction buildAction) {
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

	public BuildAction get(Object id) {
		return em.find(BuildAction.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<BuildAction> queryByExample(BuildAction model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(BuildAction.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public BuildAction update(BuildAction buildAction) {
		return em.merge(buildAction);
	}

}
