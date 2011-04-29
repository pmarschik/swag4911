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

	public Message get(Long id) {
		return em.find(Message.class, id);
	}

	@Transactional
	public Message create(Message message) {
		return em.merge(message);
	}

	@Transactional
	public Message update(Message message) {
		return em.merge(message);
	}

	@Transactional
	public void delete(Message message) {
		message = em.merge(message);
		em.remove(message);
	}
	
	public boolean contains(Message message)
	{
		return em.contains(message);
	}

}
