package swag49.dao;

import java.util.HashSet;
import java.util.Set;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import swag49.model.BuildingType;
import swag49.model.BuildingLevel;
import swag49.model.LevelBase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class BuildingLevelDaoTest {
	@Autowired @Qualifier("buildingTypeDAO")
    private DataAccessObject<BuildingType> buildingTypeDAO;
	
	@Autowired @Qualifier("buildingLevelDAO")
    private DataAccessObject<BuildingLevel> buildingLevelDAO;
    
    @Test
    public void create_shouldCreate() throws Exception {    	
    	BuildingType buildingType = new BuildingType();
    	buildingType.setName("test" + new Date().getTime());    	
    	
    	//buildingType = buildingTypeDAO.create(buildingType);
    	
    	BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);   
    	buildingLevel.setUpgradeDuration(1L);    	
    
    	buildingType = buildingTypeDAO.update(buildingType);
    	
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
//    	BuildingType buildingType = new BuildingType();
//    	buildingType.setName("test");
//    	
//    	
//    	buildingType = buildingTypeDAO.create(buildingType);
//
//    	BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1); 
//    	buildingLevel.setUpgradeDuration(1L);
//    	
//    	
//    	
//    	buildingLevel = buildingLevelDAO.create(buildingLevel);
//
//    	buildingLevelDAO.delete(buildingLevel);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
//    	BuildingType buildingType = new BuildingType();
//    	buildingType.setName("test");
//    	
//    	
//    	buildingType = buildingTypeDAO.create(buildingType);    	
//    	
//    	
//    	BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);   	
//    	buildingLevel.setUpgradeDuration(1L);
//    	buildingLevel = buildingLevelDAO.create(buildingLevel);
//    	
//    	buildingLevel = buildingLevelDAO.update(buildingLevel);
    }
}
