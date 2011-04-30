package swag49.dao;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import swag49.model.BuildingType;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class BuildingTypeDaoTest {    
    @Autowired @Qualifier("buildingTypeDAO")
    private DataAccessObject<BuildingType> buildingTypeDAO;
    
    @Test
    public void create_shouldCreate() throws Exception {    	
    	BuildingType buildingType = new BuildingType();
    	buildingType.setName("test");    	
    	
    	buildingType = buildingTypeDAO.create(buildingType);
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
    	BuildingType buildingType = new BuildingType();
    	buildingType.setName("test");
    	
    	
    	buildingType = buildingTypeDAO.create(buildingType);
    	
    	buildingTypeDAO.delete(buildingType);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
    	BuildingType buildingType = new BuildingType();
    	buildingType.setName("test");
    	
    	
    	buildingType = buildingTypeDAO.create(buildingType);
    	
    	buildingType.setName("testChange");
    	
    	buildingType = buildingTypeDAO.update(buildingType);
    }
}
