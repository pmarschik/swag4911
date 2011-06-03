package swag49.messaging;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.messaging.model.Message;
import swag49.model.User;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class MessagingTest {
    @Autowired
    @Qualifier("userDAO")
    private DataAccessObject<User> userDAO;

    @Autowired
    private MessageSender messageSender;

    @Autowired
    private MessageMaker messageMaker;

    @Autowired
    @Qualifier("emailMessageChannel")
    private MessageChannel emailMessageChannel;

    private User sender;
    private User receiver;

    @Transactional
    private User makeUser(String userName, Long playerId) {
        User user = userDAO.get(playerId);

        if (user == null) {
            user = new User();
            user.setLastName(userName);
            user.setFirstName(userName);
            user.setEmail("swag4911@gmail.com");
            user.setPassword(userName);
            user.setUsername(userName + new Date());
            user.setUtcOffset(0);

            user = userDAO.create(user);
        }

        return user;
    }

    @Before
    public void setUp() {
        sender = makeUser("message_user1", 1L);
        receiver = makeUser("message_user2", 2L);
    }

    @Test
    public void sendReceive() {
        final String SUBJECT = "subject";
        final String CONTENT = "content";
        final Date SEND_DATE = new Date();

        Message message = messageMaker.newMessage()
                .from(sender.getId())
                .to(receiver.getId())
                .on("http://foobar.com/map1")
                .at(SEND_DATE)
                .withSubjectAndContent(SUBJECT, CONTENT)
                .get();
        messageSender.send(message);

        org.springframework.integration.Message<?> receivedMessage = ((PollableChannel) emailMessageChannel).receive();
        assertThat(receivedMessage, not(is(nullValue())));

        message = (Message) receivedMessage.getPayload();

        assertThat(message.getSubject(), is(SUBJECT));
        assertThat(message.getContent(), is(CONTENT));
        assertThat(message.getSendDate(), is(SEND_DATE));
        assertThat(message.getReceiveDate(), not(is(nullValue())));
    }
}
