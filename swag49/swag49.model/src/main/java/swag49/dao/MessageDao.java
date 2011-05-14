package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="messageDAO")
public class MessageDao implements DataAccessObject<Message> {

	@PersistenceContext
	private EntityManager em;

	public MessageDao() {
	}

	public boolean contains(Message message)
	{
		return em.contains(message);
	}

	@Transactional
	public Message create(Message message) {
		return em.merge(message);
	}

	@Transactional
	public void delete(Message message) {
		message = em.merge(message);
		em.remove(message);
	}

	public Message get(Object id) {
		return em.find(Message.class, id);
	}
	
	@Transactional
	public Message update(Message message) {
		return em.merge(message);
	}

}
