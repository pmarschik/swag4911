package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

// annotate with @Repository because it is a DAO, other components should be annotated with @Component
@Repository(value="userDAO")
public class UserDao implements DataAccessObject<User> {

	@PersistenceContext(unitName = "swag49.user")
	private EntityManager em;

	public UserDao() {
	}

	public boolean contains(User user) {
		return em.contains(user);
	}

	@Transactional
	public User create(User user) {
		return em.merge(user);
	}

	@Transactional
	public void delete(User user) {
		user = em.merge(user);
		em.remove(user);
	}

	public User get(Object id) {
		return em.find(User.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<User> queryByExample(User model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(User.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public User update(User user) {
		return em.merge(user);
	}

}
