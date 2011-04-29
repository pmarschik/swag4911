package swag.dao;

import swag.model.Message;

public class MessageDao implements DataAccessObject<Message> {

	private javax.persistence.EntityManager em;

	public MessageDao(javax.persistence.EntityManager em) {
		this.em = em;
	}

	public Message get(Long id) {
		return em.find(Message.class, id);
	}

	public Message create(Message message) {
		Message temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(message);

		tx.commit();

		return temp;
	}

	public Message update(Message message) {
		Message temp = null;

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		temp = em.merge(message);

		tx.commit();

		return temp;
	}

	public void delete(Message message) {

		javax.persistence.EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(message);

		tx.commit();

	}

	public boolean contains(Message message) {
		return em.contains(message);
	}

}
