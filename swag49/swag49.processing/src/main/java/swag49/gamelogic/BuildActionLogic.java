package swag49.gamelogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import swag49.dao.DataAccessObject;
import swag49.model.*;

public class BuildActionLogic {

    @Autowired
    @Qualifier("buildingLevelDao")
    private DataAccessObject<BuildingLevel, BuildingLevel.Id> buildingLevelDao;

    @Autowired
    @Qualifier("playerDao")
    private DataAccessObject<Player, Long> playerDao;

    @Autowired
    @Qualifier("buildingDao")
    private DataAccessObject<Building, Square.Id> buildingDao;

    public void handleAction(BuildAction action) {
        Building building = action.getConcerns();

        // get current level
        BuildingLevel currentLevel = building.getIsOfLevel();

        // get next level
        // TODO: getNextLevel-Funktion??
        BuildingLevel.Id id = new BuildingLevel.Id(currentLevel.getId()
                .getLevel() + 1, currentLevel.getId().getBuildingTypeId());
        BuildingLevel nextLevel = buildingLevelDao.get(id);

        if (nextLevel != null) {
            building.setIsOfLevel(nextLevel);

            buildingDao.update(building);

            Player player = action.getPlayer();
            // update upkeep
            player.getUpkeep().remove(currentLevel.getUpkeepCosts());
            player.getUpkeep().add(nextLevel.getUpkeepCosts());

            // update income
            player.getIncome().remove(currentLevel.getUpkeepCosts());
            player.getIncome().add(nextLevel.getUpkeepCosts());

            playerDao.update(player);
        }
        // TODO: else? Errormessage?

    }
}
