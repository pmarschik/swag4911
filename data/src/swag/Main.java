package swag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import swag.dao.UserDao;
import swag.model.User;

public class Main {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		emf = Persistence.createEntityManagerFactory("swag");
		em = emf.createEntityManager();
		
		UserDao userDao = new UserDao(em);
		
		User user = new User();
		user.setLastName("test");
		
		userDao.create(user);			
		
		em.close();
		emf.close();
	}

}
