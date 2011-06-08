package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Building;
import swag49.model.Square;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("buildingDAO")
public class BuildingDAO implements DataAccessObject<Building, Square.Id> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(Building building) {
        return em.contains(building);
    }

    @Transactional("swag49.map")
    public Building create(Building building) {
        return em.merge(building);
    }

    @Transactional("swag49.map")
    public void delete(Building building) {
        building = em.merge(building);
        em.remove(building);
    }

    @Transactional("swag49.map")
    public Building get(Square.Id id) {
        return em.find(Building.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<Building> queryByExample(Building model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Building.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public Building update(Building building) {
        return em.merge(building);
    }

}