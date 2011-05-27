package swag49.gamelogic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class TroopActionLogicTest {
	private TroopActionLogic troopActionLogic;

	  @Before
	    public void setUp() {
		  troopActionLogic = new TroopActionLogic();
	    }
	    
	    @Test
	    public void test()
	    {
	    	//TODO
	    }
	
}
