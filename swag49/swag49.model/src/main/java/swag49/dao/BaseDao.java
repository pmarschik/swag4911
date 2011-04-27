package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Base;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Repository(value="baseDAO")
public class BaseDao implements DataAccessObject<Base> {

	@PersistenceContext
	private EntityManager em;

	public BaseDao() {
	}

	public Base get(Long id) {
		return em.find(Base.class, id);
	}

	@Transactional
	public Base create(Base Base) {
		return em.merge(Base);

	}

	@Transactional
	public Base update(Base Base) {
		return em.merge(Base);

	}

	@Transactional
	public void delete(Base Base) {
		em.remove(Base);

	}

	public boolean contains(Long id)
	{
		return em.contains(id);
	}
}
