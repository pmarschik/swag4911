package swag49.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import swag49.model.BuildingLevel;
import swag49.model.BuildingType;
import swag49.model.ResourceValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class BuildingLevelDaoTest {
	@Autowired
	@Qualifier("buildingTypeDAO")
	private DataAccessObject<BuildingType> buildingTypeDAO;

	@Autowired
	@Qualifier("buildingLevelDAO")
	private DataAccessObject<BuildingLevel> buildingLevelDAO;

	@Test
	public void create_shouldCreate() throws Exception {

		BuildingType buildingType = new BuildingType();
		buildingType.setName("Baracks");

		buildingType = buildingTypeDAO.create(buildingType);

		BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);

		buildingLevel.setUpgradeDuration(Long.valueOf(60));
		buildingLevel.setBuildCosts(new ResourceValue(10, 0, 0, 0));
		buildingLevel.setResourceProduction(new ResourceValue(0, 0, 0, 1));
		buildingLevel.setUpkeepCosts(new ResourceValue(0, 0, 0, 0));

		buildingLevel = buildingLevelDAO.create(buildingLevel);

		assertThat(buildingLevel, is(notNullValue()));
	}

	@Test
	public void delete_shouldDelete() throws Exception {
		BuildingType buildingType = new BuildingType();
		buildingType.setName("Baracks");

		buildingType = buildingTypeDAO.create(buildingType);

		BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);

		buildingLevel.setUpgradeDuration(Long.valueOf(60));
		buildingLevel.setBuildCosts(new ResourceValue(10, 0, 0, 0));
		buildingLevel.setResourceProduction(new ResourceValue(0, 0, 0, 1));
		buildingLevel.setUpkeepCosts(new ResourceValue(0, 0, 0, 0));

		buildingLevel = buildingLevelDAO.create(buildingLevel);

		assertThat(buildingLevel, is(notNullValue()));

		buildingLevelDAO.delete(buildingLevel);
	}

	@Test
	public void update_shouldUpdate() throws Exception {
		BuildingType buildingType = new BuildingType();
		buildingType.setName("Baracks");

		buildingType = buildingTypeDAO.create(buildingType);

		BuildingLevel buildingLevel = new BuildingLevel(buildingType, 1);

		buildingLevel.setUpgradeDuration(Long.valueOf(60));
		buildingLevel.setBuildCosts(new ResourceValue(10, 0, 0, 0));
		buildingLevel.setResourceProduction(new ResourceValue(0, 0, 0, 1));
		buildingLevel.setUpkeepCosts(new ResourceValue(0, 0, 0, 0));

		buildingLevel = buildingLevelDAO.create(buildingLevel);

		assertThat(buildingLevel, is(notNullValue()));

		buildingLevel.setUpgradeDuration(Long.valueOf(20));

		buildingLevel = buildingLevelDAO.update(buildingLevel);

		assertThat(buildingLevel.getUpgradeDuration(), is(Long.valueOf(20)));
	}
}
