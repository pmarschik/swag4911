package swag.dao;

import org.junit.Test;

import swag.model.Troop;
import swag.model.TroopType;
import swag.util.PersistenceHelper;

public class TroopDaoTest {

	private PersistenceHelper persistenceHelper;

	private DataAccessObject<Troop> troopDAO;

	public TroopDaoTest() {
		persistenceHelper = PersistenceHelper.getInstance();

		troopDAO = new TroopDao(persistenceHelper.getEm());
	}

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
	public void update_shouldUpdate() throws Exception {
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
