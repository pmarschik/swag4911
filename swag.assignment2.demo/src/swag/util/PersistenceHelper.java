package swag.util;

public class PersistenceHelper {

	private static PersistenceHelper _instance = null;
	private javax.persistence.EntityManagerFactory emf;
	private javax.persistence.EntityManager em;

	public PersistenceHelper() {
		emf = javax.persistence.Persistence.createEntityManagerFactory("swag");
		em = emf.createEntityManager();
	}

	public static PersistenceHelper getInstance() {
		if (_instance == null)
			_instance = new PersistenceHelper();

		return _instance;
	}

	public javax.persistence.EntityManager getEm() {
		return em;
	}

}
