package swag49.web.model;

import swag49.model.Tile;
import swag49.model.Troop;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Ben
 * Date: 6/10/11
 * Time: 5:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class TroopsPerTileDTO {
    public HashMap<Tile.Id, ArrayList<Troop>> getTroopsPerTile() {
        return troopsPerTile;
    }

    public void setTroopsPerTile(HashMap<Tile.Id, ArrayList<Troop>> troopsPerTile) {
        this.troopsPerTile = troopsPerTile;
    }

    private HashMap<Tile.Id, ArrayList<Troop>> troopsPerTile = new HashMap();
}
