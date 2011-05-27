package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import swag49.model.TroopType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class TroopTypeDaoTest {
	// must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("troopTypeDAO")
    private DataAccessObject<TroopType> troopTypeDAO;

    @Test
    public void create_shouldCreate() throws Exception {
    	TroopType troopType = new TroopType();
    	
    	troopType.setName("test");
    	
    	troopType = troopTypeDAO.create(troopType);
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
    	TroopType troopType = new TroopType();
    	
    	troopType.setName("test");
    	
    	troopType = troopTypeDAO.create(troopType);
    	
    	troopTypeDAO.delete(troopType);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
    	TroopType troopType = new TroopType();
    	
    	troopType.setName("test");
    	
    	troopType = troopTypeDAO.create(troopType);
    	
    	troopType = troopTypeDAO.update(troopType);
    }
}
