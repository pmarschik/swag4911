package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.BuildingLevel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("buildingLevelDAO")
public class BuildingLevelDAO implements DataAccessObject<BuildingLevel, BuildingLevel.Id> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(BuildingLevel buildingLevel) {
        return em.contains(buildingLevel);
    }

    @Transactional("swag49.map")
    public BuildingLevel create(BuildingLevel buildingLevel) {
        return em.merge(buildingLevel);
    }

    @Transactional("swag49.map")
    public void delete(BuildingLevel buildingLevel) {
        buildingLevel = em.merge(buildingLevel);
        em.remove(buildingLevel);
    }

    @Transactional("swag49.map")
    public BuildingLevel get(BuildingLevel.Id id) {
        return em.find(BuildingLevel.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<BuildingLevel> queryByExample(BuildingLevel model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(BuildingLevel.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public BuildingLevel update(BuildingLevel buildingLevel) {
        return em.merge(buildingLevel);
    }

}
