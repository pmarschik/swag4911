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
		user.setFirstName("test");
		user.setEmail("testemail");
		user.setPassword("test");
		user.setUsername("test");
		user.setUtcOffset(0);

		userDAO.create(user);
    }
    
    @Test
    public void delete_shouldDelete() {
		User user = new User();
		user.setLastName("test2");
		user.setFirstName("test2");
		user.setEmail("testemail2");
		user.setPassword("test2");
		user.setUsername("test2");
		user.setUtcOffset(0);
    	
		User user2 = userDAO.create(user);		
		//User user3 = userDAO.get(user2.getId());
		
		userDAO.delete(user2);
    }
    
    @Test
    public void update_shouldUpdate() {
		User user = new User();
		user.setLastName("test3");
		user.setFirstName("test3");
		user.setEmail("testemail3");
		user.setPassword("test3");
		user.setUsername("test3");
		user.setUtcOffset(0);
    	
		User user2 = userDAO.create(user);	
		
		user2.setLastName("AfterTest");
		
		userDAO.update(user2);
    }
}
