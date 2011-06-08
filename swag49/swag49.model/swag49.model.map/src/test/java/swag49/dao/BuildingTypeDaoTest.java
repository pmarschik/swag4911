package swag49.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import swag49.model.BuildingType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-context.xml"})
public class BuildingTypeDAOTest {

    // must use interface, qualifier is optional, use only if several beans that
    // match interface
    @Autowired
    @Qualifier("buildingTypeDAO")
    private DataAccessObject<BuildingType, Long> buildingTypeDAO;

    @Test
    public void create_shouldCreate() throws Exception {
        BuildingType buildingType = new BuildingType();

        buildingType.setName("Command Center");

        buildingType = buildingTypeDAO.create(buildingType);

        assertThat(buildingType, is(notNullValue()));
    }

    @Test
    public void delete_shouldDelete() throws Exception {
        BuildingType buildingType = new BuildingType();

        buildingType.setName("Command Center");

        buildingType = buildingTypeDAO.create(buildingType);

        buildingTypeDAO.delete(buildingType);
    }

    @Test
    public void get_shouldReturnBuildingType() throws Exception {
        BuildingType buildingType = new BuildingType();

        buildingType.setName("Command Center");

        buildingType = buildingTypeDAO.create(buildingType);

        BuildingType receivedBuildingType = buildingTypeDAO.get(buildingType
                .getId());

        assertThat(receivedBuildingType.getId(), is(buildingType.getId()));
        assertThat(receivedBuildingType.getName(), is(buildingType.getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void get_shouldThrowException() throws Exception {
        buildingTypeDAO.get(null);
    }

    @Test
    public void update_shouldUpdate() throws Exception {
        BuildingType buildingType = new BuildingType();

        buildingType.setName("Command Center");

        buildingType = buildingTypeDAO.create(buildingType);

        buildingType.setName("Ghost Academy");

        buildingTypeDAO.update(buildingType);

        BuildingType receivedBuildingType = buildingTypeDAO.get(buildingType
                .getId());

        assertThat(receivedBuildingType.getName(), is(buildingType.getName()));
    }

}
