package gamelogic;

import swag49.model.Base;
import swag49.model.ResourceValue;
import swag49.model.Square;
import swag49.model.Tile;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 03.06.11
 * Time: 12:03
 * To change this template use File | Settings | File Templates.
 */
public class BaseFactory {
    private static final int NO_SQUARES = 10;
    private static final int INCOME_WOOD = 15;
    private static final int INCOME_STONE = 15;
    private static final int INCOME_GOLD = 15;
    private static final int INCOME_CROPS = 15;

    public static Base createBase(Tile tile) {
        Base base = new Base();
        base.setLocatedOn(tile);


        //create number of squares
        Set<Square> squares = new HashSet<Square>();
        for (int i = 0; i < NO_SQUARES; i++) {
            squares.add(new Square(base, i));
        }
        base.setConsistsOf(squares);

        base.setHome(false);

        ResourceValue resourceProduction = new ResourceValue(INCOME_WOOD, INCOME_CROPS, INCOME_GOLD, INCOME_STONE);
        base.setResourceProduction(resourceProduction);

        return base;
    }
}
