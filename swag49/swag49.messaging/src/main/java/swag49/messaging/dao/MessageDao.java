package swag49.messaging.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.messaging.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "messageDAO")
public class MessageDao implements DataAccessObject<Message> {

    @PersistenceContext(unitName = "swag49.messaging")
    private EntityManager em;

    public MessageDao() {
    }

    @Transactional("swag49.messaging")
    public boolean contains(Message message) {
        return em.contains(message);
    }

    @Transactional("swag49.messaging")
    public Message create(Message message) {
        return em.merge(message);
    }

    @Transactional("swag49.messaging")
    public void delete(Message message) {
        message = em.merge(message);
        em.remove(message);
    }

    @Transactional("swag49.messaging")
    public Message get(Object id) {
        return em.find(Message.class, id);
    }

    @Transactional("swag49.messaging")
    @SuppressWarnings("unchecked")
    public List<Message> queryByExample(Message model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Message.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.messaging")
    public Message update(Message message) {
        return em.merge(message);
    }

}
