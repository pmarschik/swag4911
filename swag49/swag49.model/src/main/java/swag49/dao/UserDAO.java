package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// annotate with @Repository because it is a DAO, other components should be annotated with @Component
@Repository(value="userDAO")
public class UserDAO implements DataAccessObject<User> {
	@PersistenceContext
	private EntityManager em;

	public UserDAO() {
	}

	public User get(Long id) {
		return em.find(User.class, id);
	}

    @Transactional
	public User create(User user) {
		return em.merge(user);
	}

    @Transactional
	public User update(User user) {
		return em.merge(user);
	}

    @Transactional
	public void delete(User user) {
		em.remove(user);
	}

	public boolean contains(Long id)
	{
		return em.contains(id);
	}
}
