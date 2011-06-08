package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopLevel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository(value = "troopLevelDAO")
public class TroopLevelDAO implements DataAccessObject<TroopLevel, TroopLevel.Id> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(TroopLevel troopLevel) {
        return em.contains(troopLevel);
    }

    @Transactional("swag49.map")
    public TroopLevel create(TroopLevel troopLevel) {
        return em.merge(troopLevel);
    }

    @Transactional("swag49.map")
    public void delete(TroopLevel troopLevel) {
        troopLevel = em.merge(troopLevel);
        em.remove(troopLevel);
    }

    @Transactional("swag49.map")
    public TroopLevel get(TroopLevel.Id id) {
        return em.find(TroopLevel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<TroopLevel> queryByExample(TroopLevel model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(TroopLevel.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public TroopLevel update(TroopLevel troopLevel) {
        return em.merge(troopLevel);
    }

}
