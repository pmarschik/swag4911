package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Troop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//annotate with @Repository because it is a DAO, other components should be annotated with @Component
@Repository("troopDAO")
public class TroopDAO implements DataAccessObject<Troop, Long> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(Troop troop) {
        return em.contains(troop);
    }

    @Transactional("swag49.map")
    public Troop create(Troop troop) {
        return em.merge(troop);
    }

    @Transactional("swag49.map")
    public void delete(Troop troop) {
        troop = em.merge(troop);
        em.remove(troop);
    }

    @Transactional("swag49.map")
    public Troop get(Long id) {
        return em.find(Troop.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<Troop> queryByExample(Troop model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Troop.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public Troop update(Troop troop) {
        return em.merge(troop);
    }

}
