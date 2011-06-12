package swag49.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.transaction.annotation.Transactional;
import swag49.model.ResourceValue;
import swag49.model.Tile;
import swag49.model.Troop;
import swag49.model.TroopLevel;
import swag49.model.TroopType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class TroopDAOTest {
	// must use interface, qualifier is optional, use only if several beans that
	// match interface
	@Autowired
	@Qualifier("troopDAO")
	private DataAccessObject<Troop, Long> troopDAO;

	@Autowired
	@Qualifier("troopLevelDAO")
	private DataAccessObject<TroopLevel, TroopLevel.Id> troopLevelDAO;

	@Autowired
	@Qualifier("troopTypeDAO")
	private DataAccessObject<TroopType, Long> troopTypeDAO;

	private TroopType troopType = null;
	private TroopLevel troopLevel = null;
	private Tile position = null;
	private Tile position2 = null;

	@Test
    @Transactional("swag49.map")
	public void create_shouldCreate() throws Exception {

		Troop troop = new Troop();

		troop.setPosition(position);
		troop.setType(troopType);
		troop.setIsOfLevel(troopLevel);

		troop = troopDAO.create(troop);
	}

	private void createLevelAndType() {
		troopType = new TroopType();
		troopType.setName("Mutalisk");

		troopType = troopTypeDAO.create(troopType);

		troopLevel = new TroopLevel(troopType, 1);

		troopLevel.setUpgradeDuration(Long.valueOf(10));
		troopLevel.setBuildCosts(new ResourceValue(0, 0, 0, 5));
		troopLevel.setUpkeepCosts(new ResourceValue(0, 0, 0, 1));

		troopLevel.setSpeed(Integer.valueOf(10));
		troopLevel.setDefense(Integer.valueOf(100));
		troopLevel.setStrength(Integer.valueOf(20));

		troopLevel = troopLevelDAO.create(troopLevel);

	}

	@Test
    @Transactional("swag49.map")
	public void delete_shouldDelete() throws Exception {

		Troop troop = new Troop();
		troop.setPosition(position);
		troop.setType(troopType);
		troop.setIsOfLevel(troopLevel);

		troop = troopDAO.create(troop);

		troopDAO.delete(troop);
	}

	@Before
	public void setUp() {
		createLevelAndType();
	}

	@Test
    @Transactional("swag49.map")
	public void update_shouldUpdate() throws Exception {

		Troop troop = new Troop();

		troop.setPosition(position);
		troop.setType(troopType);
		troop.setIsOfLevel(troopLevel);

		troop = troopDAO.create(troop);

		troop.setPosition(position2);

		troopDAO.update(troop);
	}
}
