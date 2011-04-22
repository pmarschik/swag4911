package swag.dao;

import swag.model.User;

public class UserDao implements IDAO<User> {

	private javax.persistence.EntityManager em;

	public UserDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public User get(Long id) {
		return em.find(User.class, id);
	}

	public void create(User user) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(user);

		tx.commit();

	}

	public void update(User user) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(user);

		tx.commit();

	}

	public void delete(User user) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(user);

		tx.commit();

	}

}
