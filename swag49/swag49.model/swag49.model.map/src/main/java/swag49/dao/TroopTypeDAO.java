package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "troopTypeDAO")
public class TroopTypeDAO implements DataAccessObject<TroopType, Long> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(TroopType troopType) {
        return em.contains(troopType);
    }

    @Transactional("swag49.map")
    public TroopType create(TroopType troopType) {
        return em.merge(troopType);
    }

    @Transactional("swag49.map")
    public void delete(TroopType troopType) {
        troopType = em.merge(troopType);
        em.remove(troopType);
    }

    @Transactional("swag49.map")
    public TroopType get(Long id) {
        return em.find(TroopType.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<TroopType> queryByExample(TroopType model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(TroopType.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public TroopType update(TroopType troopType) {
        return em.merge(troopType);
    }

}
