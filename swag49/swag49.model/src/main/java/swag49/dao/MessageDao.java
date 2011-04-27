package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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
	public Message create(Message Message) {
		return em.merge(Message);
	}

	@Transactional
	public Message update(Message Message) {
		return em.merge(Message);
	}

	@Transactional
	public void delete(Message Message) {
		em.remove(Message);
	}
	
	public boolean contains(Long id)
	{
		return em.contains(id);
	}

}
