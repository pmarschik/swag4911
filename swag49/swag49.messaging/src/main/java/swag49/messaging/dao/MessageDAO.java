package swag49.messaging.dao;

import org.springframework.stereotype.Repository;
import swag49.dao.AbstractDataAccessObject;
import swag49.messaging.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("messageDAO")
public class MessageDAO extends AbstractDataAccessObject<Message, Long> {
    @PersistenceContext(unitName = "swag49.messaging")
    private EntityManager em;

    public MessageDAO() {
        super(Message.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
