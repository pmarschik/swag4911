package swag.dao;

import swag.model.User;

public class UserDao implements DataAccessObject<User> {

	private javax.persistence.EntityManager em;

	public UserDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public User get(Long id) {
		return em.find(User.class, id);
	}

	public User create(User user) {
		User temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(user);

		tx.commit();

		return temp;
	}

	public User update(User user) {
		User temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(user);

		tx.commit();

		return temp;
	}

	public void delete(User user) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(user);

		tx.commit();

	}

	public boolean contains(User user) {
		return em.contains(user);
	}

}
