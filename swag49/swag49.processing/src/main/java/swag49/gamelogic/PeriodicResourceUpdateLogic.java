package swag49.gamelogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import swag49.dao.DataAccessObject;
import swag49.model.Player;
import swag49.model.ResourceValue;

import java.util.Collection;

public class PeriodicResourceUpdateLogic {

    @Autowired
    @Qualifier("buildingLevelDao")
    private DataAccessObject<Player> playerDao;

    // TODO: "periodisch" machen
    // http://static.springsource.org/spring/docs/current/spring-framework-reference/html/scheduling.html

    public void updateResources() {
        //TODO null oder empty Player??
        Collection<Player> players = playerDao.queryByExample(null);

        for (Player player : players) {

            // get current resource-values
            ResourceValue resources = player.getResources();

            // add income
            resources.add(player.getIncome());

            resources.remove(player.getUpkeep());

            // set minimal amount to zero
            resources.setAmount_gold(Math.max(0, resources.getAmount_gold()));
            resources.setAmount_crops(Math.max(0, resources.getAmount_crops()));
            resources.setAmount_stone(Math.max(0, resources.getAmount_stone()));
            resources.setAmount_wood(Math.max(0, resources.getAmount_wood()));

            playerDao.update(player);
        }
    }

}
