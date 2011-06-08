package gamelogic;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.model.*;
import swag49.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
    private DataAccessObject<Map, Long> mapDAO;

    @Autowired
    @Qualifier("baseDAO")
    private DataAccessObject<Base, Long> baseDAO;

    @Autowired
    @Qualifier("squareDAO")
    private DataAccessObject<Square, Square.Id> squareDAO;

    @Autowired
    @Qualifier("playerDAO")
    private DataAccessObject<Player, Long> playerDAO;

    @Autowired
    @Qualifier("tileDAO")
    private DataAccessObject<Tile, Tile.Id> tileDAO;

    private static final int RANDOMTRIES = 1000;
    private static final int START_AMOUNT_WOOD = 100;
    private static final int START_AMOUNT_STONE = 100;
    private static final int START_AMOUNT_GOLD = 100;
    private static final int START_AMOUNT_CROPS = 100;

    private static final int NO_SQUARES = 10;
    private static final int INCOME_WOOD = 15;
    private static final int INCOME_STONE = 15;
    private static final int INCOME_GOLD = 15;
    private static final int INCOME_CROPS = 15;

    @Log
    private static Logger logger;

    @Transactional
    public void initializePlayer(Map map, Player player) {
        map = mapDAO.get(map.getId());
        Tile homeBaseTile = findHomeBaseLocation(map);


        Base homeBase = createBase(homeBaseTile, player);
        homeBase.setHome(true);

        baseDAO.update(homeBase);
        tileDAO.update(homeBaseTile);

        player.setIncome(homeBase.getResourceProduction());
        player.setUpkeep(new ResourceValue());
        player.setResources(
                new ResourceValue(START_AMOUNT_WOOD, START_AMOUNT_CROPS, START_AMOUNT_GOLD, START_AMOUNT_STONE));

        player = playerDAO.update(player);

    }

    @Transactional
    private Tile findHomeBaseLocation(Map map) {
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


    public Base createBase(Tile tile, Player owner) {
        Base base = new Base(tile);


        base.setLocatedOn(tile);
        base.setHome(false);
        base.setOwner(owner);
        base = baseDAO.create(base);

        Set<Square> squares = new HashSet<Square>();
        for (int i = 0; i < NO_SQUARES; i++) {

            squares.add(squareDAO.create(new Square(base, i)));
        }

        base.setConsistsOf(squares);

        ResourceValue resourceProduction = new ResourceValue(INCOME_WOOD, INCOME_CROPS, INCOME_GOLD, INCOME_STONE);
        base.setResourceProduction(resourceProduction);
        base = baseDAO.update(base);

        tile.setBase(base);
        tileDAO.update(tile);

        return base;
    }

    public static void intializeBase(Base base) {
        //create number of squares
        Set<Square> squares = new HashSet<Square>();
        for (int i = 0; i < NO_SQUARES; i++) {
            squares.add(new Square(base, i));
        }
        base.setConsistsOf(squares);

        ResourceValue resourceProduction = new ResourceValue(INCOME_WOOD, INCOME_CROPS, INCOME_GOLD, INCOME_STONE);
        base.setResourceProduction(resourceProduction);

    }

}
