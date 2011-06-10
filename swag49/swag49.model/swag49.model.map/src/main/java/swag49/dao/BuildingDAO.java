package swag49.dao;

import org.springframework.stereotype.Repository;
import swag49.model.Building;
import swag49.model.Square;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("buildingDAO")
public class BuildingDAO extends AbstractDataAccessObject<Building, Square.Id> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public BuildingDAO() {
        super(Building.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}