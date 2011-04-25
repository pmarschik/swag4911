package swag49.dao;

import swag49.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

public class MessageDao implements DataAccessObject<Message> {

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
	
	public boolean contains(Long id)
	{
		return em.contains(id);
	}

}
