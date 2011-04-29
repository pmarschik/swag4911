package swag49.dao;

import swag49.model.Troop;
import swag49.model.TroopType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class TroopDaoTest {
    // must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired @Qualifier("troopDAO")
    private DataAccessObject<Troop> troopDAO;

    @Test
    public void create_shouldCreate() throws Exception {
    	Troop troop = new Troop();
    	troop.setLevel(1);
    	troop.setDefense(1);
    	troop.setSpeed(1);
    	troop.setStrength(1);
    	troop.setType(TroopType.BOWMEN);
    	
    	troop = troopDAO.create(troop);
    }
    
    @Test
    public void delete_shouldDelete() throws Exception {
    	Troop troop = new Troop();
    	troop.setLevel(1);
    	troop.setDefense(1);
    	troop.setSpeed(1);
    	troop.setStrength(1);
    	troop.setType(TroopType.BOWMEN);
    	
    	troop = troopDAO.create(troop);
    	
    	troopDAO.delete(troop);
    }
    
    @Test
    public void update_shouldUpdate() throws Exception{
    	Troop troop = new Troop();
    	troop.setLevel(1);
    	troop.setDefense(1);
    	troop.setSpeed(1);
    	troop.setStrength(1);
    	troop.setType(TroopType.BOWMEN);
    	
    	troop = troopDAO.create(troop);
    	
    	troop.setDefense(2);
    	
    	troopDAO.update(troop);
    }
}
