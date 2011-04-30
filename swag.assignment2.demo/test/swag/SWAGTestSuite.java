package swag;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import swag.dao.BaseDaoTest;
import swag.dao.MapDaoTest;
import swag.dao.MessageDaoTest;
import swag.dao.PlayerDaoTest;
import swag.dao.ResourceDaoTest;
import swag.dao.SquareDaoTest;
import swag.dao.TileDaoTest;
import swag.dao.TroopActionDaoTest;
import swag.dao.TroopDaoTest;
import swag.dao.UserDAOTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BaseDaoTest.class, MapDaoTest.class,
		MessageDaoTest.class, PlayerDaoTest.class, ResourceDaoTest.class,
		SquareDaoTest.class, TileDaoTest.class, TroopActionDaoTest.class,
		TroopDaoTest.class, UserDAOTest.class })
public class SWAGTestSuite {

}
