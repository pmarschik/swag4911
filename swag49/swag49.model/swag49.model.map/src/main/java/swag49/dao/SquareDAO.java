package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Square;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("squareDAO")
@Transactional("swag49.map")
public class SquareDAO extends AbstractDataAccessObject<Square, Square.Id> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public SquareDAO() {
        super(Square.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
