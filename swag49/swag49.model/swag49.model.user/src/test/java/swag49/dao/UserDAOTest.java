package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.User;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class UserDAOTest {
    // must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("userDAO")
    private DataAccessObject<User, String> userDAO;

    @Test
    @Transactional("swag49.user")
    public void create_shouldCreate() throws Exception {
		User user = new User();
		user.setLastName("test");
		user.setFirstName("test");
		user.setEmail("testemail");
		user.setPassword("test");
		user.setUsername("test" + new Date().getTime());
		user.setUtcOffset(0);

		userDAO.create(user);
    }

    @Test
    @Transactional("swag49.user")
    public void delete_shouldDelete() throws Exception {
		User user = new User();
		user.setLastName("test2");
		user.setFirstName("test2");
		user.setEmail("testemail2");
		user.setPassword("test2");
		user.setUsername("test2" + new Date().getTime());
		user.setUtcOffset(0);

		User user2 = userDAO.create(user);
		//User user3 = userDAO.get(user2.getId());

		userDAO.delete(user2);
    }

    @Test
    @Transactional("swag49.user")
    public void update_shouldUpdate() throws Exception{
		User user = new User();
		user.setLastName("test3");
		user.setFirstName("test3");
		user.setEmail("testemail3");
		user.setPassword("test3");
		user.setUsername("test3" + new Date().getTime());
		user.setUtcOffset(0);

		User user2 = userDAO.create(user);

		user2.setLastName("AfterTest");

		userDAO.update(user2);
    }
}
