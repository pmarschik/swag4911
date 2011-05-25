package swag49.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import swag49.model.Base;

@Repository(value = "baseDAO")
public class BaseDao implements DataAccessObject<Base> {

	@PersistenceContext
	private EntityManager em;

	public BaseDao() {
	}

	public boolean contains(Base base) {
		return em.contains(base);
	}

	@Transactional
	public Base create(Base base) {
		return em.merge(base);

	}

	@Transactional
	public void delete(Base base) {
		base = em.merge(base);
		em.remove(base);

	}

	public Base get(Object id) {
		return em.find(Base.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Base> queryByExample(Base model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Base.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public Base update(Base base) {
		return em.merge(base);

	}

}
