package swag49.model.helper;

import swag49.model.ResourceValue;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 11.06.11
 * Time: 10:29
 * To change this template use File | Settings | File Templates.
 */
public class ResourceValueHelper {
    public static void add(ResourceValue value1, ResourceValue value2) {

        value1.setAmount_crops(value1.getAmount_crops() + value2.getAmount_crops());
        value1.setAmount_stone(value1.getAmount_stone() + value2.getAmount_stone());
        value1.setAmount_gold(value1.getAmount_gold() + value2.getAmount_gold());
        value1.setAmount_wood(value1.getAmount_wood() + value2.getAmount_wood());
    }

    public static void remove(ResourceValue value1, ResourceValue value2) {
        value1.setAmount_crops(value1.getAmount_crops() - value2.getAmount_crops());
        value1.setAmount_stone(value1.getAmount_stone() - value2.getAmount_stone());
        value1.setAmount_gold(value1.getAmount_gold() - value2.getAmount_gold());
        value1.setAmount_wood(value1.getAmount_wood() - value2.getAmount_wood());
    }


    public static boolean isZero(ResourceValue value) {
        if (value.getAmount_crops() > 0)
            return false;

        if (value.getAmount_gold() > 0)
            return false;

        if (value.getAmount_stone() > 0)
            return false;

        if (value.getAmount_wood() > 0)
            return false;

        return true;
    }


    public static boolean geq(ResourceValue value1, ResourceValue value2) {
        if (value1.getAmount_crops() < value2.getAmount_crops())
            return false;
        if (value1.getAmount_gold() < value2.getAmount_gold())
            return false;
        if (value1.getAmount_stone() < value2.getAmount_stone())
            return false;
        if (value1.getAmount_wood() < value2.getAmount_wood())
            return false;

        return true;
    }
}
