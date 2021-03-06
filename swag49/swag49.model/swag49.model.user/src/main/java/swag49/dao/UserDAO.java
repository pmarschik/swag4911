package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// annotate with @Repository because it is a DAO, other components should be annotated with @Component
@Repository(value="userDAO")
@Transactional("swag49.user")
public class UserDAO extends AbstractDataAccessObject<User, String> {
    @PersistenceContext(unitName = "swag49.user")
    private EntityManager em;

    public UserDAO() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
