package swag49.dao;

import swag49.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDao2 implements DataAccessObject<User> {

	private EntityManager em;

	public UserDao2(EntityManager em) {
		this.em = em;
	}

	public User get(Long id) {
		return em.find(User.class, id);
	}

	public void create(User user) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(user);

		tx.commit();

	}

	public void update(User user) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(user);

		tx.commit();

	}

	public void delete(User user) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(user);

		tx.commit();

	}
	
	public boolean contains(Long id)
	{
		return em.contains(id);
	}

}
