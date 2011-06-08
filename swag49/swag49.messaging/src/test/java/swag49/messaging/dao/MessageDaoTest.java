package swag49.messaging.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import swag49.dao.DataAccessObject;
import swag49.messaging.model.Message;

import java.util.Date;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class MessageDaoTest {
    // must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired
    @Qualifier("messageDAO")
    private DataAccessObject<Message> messageDAO;

    @Test
    public void create_shouldCreate() throws Exception {
        Message message = new Message();
        message.setContent("test");
        message.setReceiverUserId(1L);
        message.setSenderUserId(2L);
        message.setSubject("test");
        message.setMapUrl("http://rofl.com");
        message.setSendDate(new Date());

        message = messageDAO.create(message);
    }

    @Test
    public void delete_shouldDelete() throws Exception {
        Message message = new Message();
        message.setContent("test");
        message.setReceiverUserId(1L);
        message.setSenderUserId(2L);
        message.setSubject("test");
        message.setMapUrl("http://rofl.com");
        message.setSendDate(new Date());

        message = messageDAO.create(message);

        messageDAO.delete(message);
    }

    @Test
    public void update_shouldUpdate() throws Exception {
        Message message = new Message();
        message.setContent("test");
        message.setReceiverUserId(1L);
        message.setSenderUserId(2L);
        message.setSubject("test");
        message.setMapUrl("http://rofl.com");
        message.setSendDate(new Date());

        message = messageDAO.create(message);

        message.setSubject("New Subject");

        messageDAO.update(message);
    }
}
