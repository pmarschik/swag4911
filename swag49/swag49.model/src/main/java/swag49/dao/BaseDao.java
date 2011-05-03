package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="baseDAO")
public class BaseDao implements DataAccessObject<Base> {

	@PersistenceContext
	private EntityManager em;

	public BaseDao() {
	}

	public boolean contains(Base base)
	{
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

	public Base get(Long id) {
		return em.find(Base.class, id);
	}

	@Transactional
	public Base update(Base base) {
		return em.merge(base);

	}
}
