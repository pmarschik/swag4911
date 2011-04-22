package swag;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
	
	private static EntityManagerFactory emf;
	private static EntityManager em;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		emf = Persistence.createEntityManagerFactory("swag");
		em = emf.createEntityManager();
		
		em.close();
		emf.close();
	}

}
