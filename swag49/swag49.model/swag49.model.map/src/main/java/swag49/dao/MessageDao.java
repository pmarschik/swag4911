package swag49.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import swag49.model.Message;

@Repository(value = "messageDAO")
public class MessageDao implements DataAccessObject<Message> {

	@PersistenceContext
	private EntityManager em;

	public MessageDao() {
	}

	public boolean contains(Message message) {
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

	@SuppressWarnings("unchecked")
	public Collection<Message> queryByExample(Message model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Message.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public Message update(Message message) {
		return em.merge(message);
	}

}
