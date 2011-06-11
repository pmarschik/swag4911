package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.BuildingType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("buildingTypeDAO")
@Transactional("swag49.map")
public class BuildingTypeDAO extends AbstractDataAccessObject<BuildingType, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public BuildingTypeDAO() {
        super(BuildingType.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
