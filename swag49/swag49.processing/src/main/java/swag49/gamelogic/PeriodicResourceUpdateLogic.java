package swag49.gamelogic;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.model.Player;
import swag49.model.ResourceValue;
import swag49.util.Log;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Component("periodicResourceUpdateLogic")
public class PeriodicResourceUpdateLogic  {

    @Autowired
    @Qualifier("playerDAO")
    private DataAccessObject<Player, Long> playerDAO;

    @Log
    private Logger logger;

    @PersistenceContext
    private EntityManager em;


    // http://static.springsource.org/spring/docs/current/spring-framework-reference/html/scheduling.html

    @Transactional("swag49.map")
    public void updateResources() {
        logger.info("Start updating resources");
        //TODO null oder empty Player??
        Collection<Player> players = playerDAO.queryByExample(new Player());

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

            playerDAO.update(player);
        }

         logger.info("Finished updating resources");
    }



}
