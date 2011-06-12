package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class TroopTypeDAOTest {
    // must use interface, qualifier is optional, use only if several beans that match interface
    @Autowired
    @Qualifier("troopTypeDAO")
    private DataAccessObject<TroopType, Long> troopTypeDAO;

    @Test
    @Transactional("swag49.map")
    public void create_shouldCreate() throws Exception {
        TroopType troopType = new TroopType();

        troopType.setName("test");

        troopType = troopTypeDAO.create(troopType);
    }

    @Test
    @Transactional("swag49.map")
    public void delete_shouldDelete() throws Exception {
        TroopType troopType = new TroopType();

        troopType.setName("test");

        troopType = troopTypeDAO.create(troopType);

        troopTypeDAO.delete(troopType);
    }

    @Test
    @Transactional("swag49.map")
    public void update_shouldUpdate() throws Exception {
        TroopType troopType = new TroopType();

        troopType.setName("test");

        troopType = troopTypeDAO.create(troopType);

        troopType = troopTypeDAO.update(troopType);
    }
}
