package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import swag49.model.TroopType;
import swag49.model.TroopLevel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class TroopLevelDaoTest {
	// must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("troopTypeDAO")
    private DataAccessObject<TroopType> troopTypeDAO;
    
    @Autowired @Qualifier("troopLevelDAO")
    private DataAccessObject<TroopLevel> troopLevelDAO;

    @Test
    public void create_shouldCreate() throws Exception {
//    	TroopType troopType = new TroopType();
//    	
//    	troopType.setName("test");
//    	
//    	troopType = troopTypeDAO.create(troopType);
//    	
//    	TroopLevel troopLevel = new TroopLevel(troopType, 1);
//    	troopLevel.setUpgradeDuration(10L);
//    	troopLevel = troopLevelDAO.create(troopLevel);
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
//    	TroopType troopType = new TroopType();
//    	
//    	troopType.setName("test");
//    	
//    	troopType = troopTypeDAO.create(troopType);
//    	
//    	TroopLevel troopLevel = new TroopLevel(troopType, 1);
//    	troopLevel.setUpgradeDuration(10L);
//    	troopLevel = troopLevelDAO.create(troopLevel);
//    	
//    	troopLevelDAO.delete(troopLevel);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
//    	TroopType troopType = new TroopType();
//    	
//    	troopType.setName("test");
//    	
//    	troopType = troopTypeDAO.create(troopType);
//    	
//    	TroopLevel troopLevel = new TroopLevel(troopType, 1);
//    	troopLevel.setUpgradeDuration(10L);
//    	troopLevel = troopLevelDAO.create(troopLevel);
//    	
//    	troopLevel = troopLevelDAO.update(troopLevel);
    }
}
