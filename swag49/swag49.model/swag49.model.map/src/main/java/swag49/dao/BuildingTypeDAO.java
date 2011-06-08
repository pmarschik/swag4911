package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.BuildingType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("buildingTypeDAO")
public class BuildingTypeDAO implements DataAccessObject<BuildingType, Long> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(BuildingType buildingType) {
        return em.contains(buildingType);
    }

    @Transactional("swag49.map")
    public BuildingType create(BuildingType buildingType) {
        return em.merge(buildingType);
    }

    @Transactional("swag49.map")
    public void delete(BuildingType buildingType) {
        buildingType = em.merge(buildingType);
        em.remove(buildingType);
    }

    @Transactional("swag49.map")
    public BuildingType get(Long id) {
        return em.find(BuildingType.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<BuildingType> queryByExample(BuildingType model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(BuildingType.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public BuildingType update(BuildingType buildingType) {
        return em.merge(buildingType);
    }

}
