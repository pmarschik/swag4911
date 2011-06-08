package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("mapDAO")
public class MapDAO implements DataAccessObject<Map, Long> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(Map map) {
        return em.contains(map);
    }

    @Transactional("swag49.map")
    public Map create(Map map) {
        return em.merge(map);
    }

    @Transactional("swag49.map")
    public void delete(Map map) {
        map = em.merge(map);
        em.remove(map);
    }

    @Transactional("swag49.map")
    public Map get(Long id) {
        return em.find(Map.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<Map> queryByExample(Map model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Map.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public Map update(Map map) {
        return em.merge(map);
    }

}
