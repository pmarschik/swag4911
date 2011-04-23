package swag.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import swag.model.Message;

public class MessageDao implements IDao<Message> {

	@PersistenceContext
	private EntityManager em;

	public MessageDao(EntityManager em) {
		this.em = em;
	}

	public Message get(Long id) {
		return em.find(Message.class, id);
	}

	public void create(Message Message) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.persist(Message);

		tx.commit();

	}

	public void update(Message Message) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.merge(Message);

		tx.commit();

	}

	public void delete(Message Message) {

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		em.remove(Message);

		tx.commit();

	}
	
	public boolean contains(Message message)
	{
		return em.contains(message);
	}

}
