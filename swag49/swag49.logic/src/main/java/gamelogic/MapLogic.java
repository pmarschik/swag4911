package gamelogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.model.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 05.06.11
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */
public class MapLogic {

    @Autowired
    @Qualifier("mapDAO")
    private DataAccessObject<Map> mapDAO;

    @Autowired
    @Qualifier("playerDAO")
    private DataAccessObject<Player> playerDAO;
    private static final int RANDOMTRIES = 1000;
    private static final int START_AMOUNT_WOOD = 100;
    private static final int START_AMOUNT_STONE = 100;
    private static final int START_AMOUNT_GOLD = 100;
    private static final int START_AMOUNT_CROPS = 100;

    @Transactional
    public static void initializePlayer(Map map, Player player) {

        Tile homeBaseTile = findHomeBaseLocation(map);

        Base homeBase = BaseFactory.createBase(homeBaseTile);

        homeBase.setHome(true);
        homeBase.setOwner(player);

        player.setIncome(homeBase.getResourceProduction());
        player.setUpkeep(new ResourceValue());
        player.setResources(new ResourceValue(START_AMOUNT_WOOD, START_AMOUNT_CROPS, START_AMOUNT_GOLD, START_AMOUNT_STONE));

    }

    private static Tile findHomeBaseLocation(Map map) {
        //try random
        Random rnd = new Random(0);

        ArrayList<Tile> tiles = new ArrayList(map.getConsistsOf());
        int tileNo = tiles.size();
        for (int i = 0; i < RANDOMTRIES; i++) {
            int j = rnd.nextInt(tileNo);
            Tile tile = tiles.get(j);
            if (tile.getBase() != null || tile.getSpecial() != ResourceType.NONE)
                continue;
            else {
                return tile;
            }
        }

        //check all sequentially
        for (Tile tile : tiles) {
            if (tile.getBase() != null || tile.getSpecial() != ResourceType.NONE)
                continue;
            else {
                return tile;
            }
        }
        return null;
    }


}
