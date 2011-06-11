package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.BuildingLevel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("buildingLevelDAO")
@Transactional("swag49.map")
public class BuildingLevelDAO extends AbstractDataAccessObject<BuildingLevel, BuildingLevel.Id> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public BuildingLevelDAO() {
        super(BuildingLevel.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
