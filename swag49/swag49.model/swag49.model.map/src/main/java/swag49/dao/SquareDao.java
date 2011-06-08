package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Square;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("squareDAO")
public class SquareDAO implements DataAccessObject<Square, Square.Id> {

    @PersistenceContext(unitName = "swag49.map")
    private javax.persistence.EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(Square square) {
        return em.contains(square);
    }

    @Transactional("swag49.map")
    public Square create(Square square) {
        em.merge(square);
        return square;
    }

    @Transactional("swag49.map")
    public void delete(Square square) {
        square = em.merge(square);
        em.remove(square);

    }

    @Transactional("swag49.map")
    public Square get(Square.Id id) {
        return em.find(Square.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<Square> queryByExample(Square model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Square.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public Square update(Square square) {
        em.merge(square);
        return square;
    }

}
