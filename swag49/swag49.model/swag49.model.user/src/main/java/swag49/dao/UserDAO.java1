package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// annotate with @Repository because it is a DAO, other components should be annotated with @Component
@Repository(value="userDAO")
public class UserDAO implements DataAccessObject<User, Long> {

	@PersistenceContext(unitName = "swag49.user")
	private EntityManager em;

	public UserDAO() {
	}

    @Transactional("swag49.user")
	public boolean contains(User user) {
		return em.contains(user);
	}

	@Transactional("swag49.user")
	public User create(User user) {
		return em.merge(user);
	}

	@Transactional("swag49.user")
	public void delete(User user) {
		user = em.merge(user);
		em.remove(user);
	}

    @Transactional("swag49.user")
	public User get(Long id) {
		return em.find(User.class, id);
	}

	@SuppressWarnings("unchecked")
    @Transactional("swag49.user")
	public List<User> queryByExample(User model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(User.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional("swag49.user")
	public User update(User user) {
		return em.merge(user);
	}

}
