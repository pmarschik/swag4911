package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import swag49.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class UserDAOTest {
    // must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("userDAO")
    private DataAccessObject<User> userDAO;

    @Test
    public void create_shouldCreate() {
		User user = new User();
		user.setLastName("test");

		userDAO.create(user);
    }
}
