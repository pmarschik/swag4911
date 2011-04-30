package swag.dao;

import java.util.Date;

import org.junit.Test;

import swag.model.User;
import swag.util.PersistenceHelper;

public class UserDAOTest {

	private PersistenceHelper persistenceHelper;

	private DataAccessObject<User> userDAO;

	public UserDAOTest() {
		persistenceHelper = PersistenceHelper.getInstance();

		userDAO = new UserDao(persistenceHelper.getEm());
	}

	@Test
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
	public void delete_shouldDelete() throws Exception {
		User user = new User();
		user.setLastName("test2");
		user.setFirstName("test2");
		user.setEmail("testemail2");
		user.setPassword("test2");
		user.setUsername("test2" + new Date().getTime());
		user.setUtcOffset(0);

		User user2 = userDAO.create(user);
		// User user3 = userDAO.get(user2.getId());

		userDAO.delete(user2);
	}

	@Test
	public void update_shouldUpdate() throws Exception {
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
