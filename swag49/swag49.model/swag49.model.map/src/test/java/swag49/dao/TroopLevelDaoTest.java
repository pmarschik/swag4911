package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import swag49.model.ResourceValue;
import swag49.model.TroopLevel;
import swag49.model.TroopType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class TroopLevelDAOTest {

	@Autowired
	@Qualifier("troopLevelDAO")
	private DataAccessObject<TroopLevel, TroopLevel.Id> troopLevelDAO;

	@Autowired
	@Qualifier("troopTypeDAO")
	private DataAccessObject<TroopType, Long> troopTypeDAO;

	@Test
	public void create_shouldCreate() throws Exception {

		TroopType troopType = new TroopType();
		troopType.setName("Mutalisk");

		troopType = troopTypeDAO.create(troopType);

		TroopLevel troopLevel = new TroopLevel(troopType, 1);

		troopLevel.setUpgradeDuration(Long.valueOf(10));
		troopLevel.setBuildCosts(new ResourceValue(0, 0, 0, 5));
		troopLevel.setUpkeepCosts(new ResourceValue(0, 0, 0, 1));
		troopLevel.setSpeed(Integer.valueOf(10));
		troopLevel.setDefense(Integer.valueOf(100));
		troopLevel.setStrength(Integer.valueOf(20));

		troopLevel = troopLevelDAO.create(troopLevel);

		assertThat(troopLevel, is(notNullValue()));
	}

	@Test
	public void delete_shouldDelete() throws Exception {
		TroopType troopType = new TroopType();
		troopType.setName("Mutalisk");

		troopType = troopTypeDAO.create(troopType);

		TroopLevel troopLevel = new TroopLevel(troopType, 1);

		troopLevel.setUpgradeDuration(Long.valueOf(10));
		troopLevel.setBuildCosts(new ResourceValue(0, 0, 0, 5));
		troopLevel.setUpkeepCosts(new ResourceValue(0, 0, 0, 1));

		troopLevel.setSpeed(Integer.valueOf(10));
		troopLevel.setDefense(Integer.valueOf(100));
		troopLevel.setStrength(Integer.valueOf(20));

		troopLevel = troopLevelDAO.create(troopLevel);


		troopLevelDAO.delete(troopLevel);
	}

	@Test
	public void update_shouldUpdate() throws Exception {
		TroopType troopType = new TroopType();
		troopType.setName("Mutalisk");

		troopType = troopTypeDAO.create(troopType);

		TroopLevel troopLevel = new TroopLevel(troopType, 1);

		troopLevel.setUpgradeDuration(Long.valueOf(10));
		troopLevel.setBuildCosts(new ResourceValue(0, 0, 0, 5));
		troopLevel.setUpkeepCosts(new ResourceValue(0, 0, 0, 1));

		troopLevel.setSpeed(Integer.valueOf(10));
		troopLevel.setDefense(Integer.valueOf(100));
		troopLevel.setStrength(Integer.valueOf(20));

		troopLevel = troopLevelDAO.create(troopLevel);

		troopLevel.setUpgradeDuration(Long.valueOf(20));

		troopLevel = troopLevelDAO.update(troopLevel);

		assertThat(troopLevel.getUpgradeDuration(), is(Long.valueOf(20)));
	}

}
