package swag49.web.model;

import com.google.common.collect.Maps;
import swag49.model.Tile;
import swag49.model.Troop;

import java.util.ArrayList;
import java.util.HashMap;

public class TroopsPerTileDTO {
    public HashMap<Tile.Id, ArrayList<Troop>> getTroopsPerTile() {
        return troopsPerTile;
    }

    public void setTroopsPerTile(HashMap<Tile.Id, ArrayList<Troop>> troopsPerTile) {
        this.troopsPerTile = troopsPerTile;
    }

    private HashMap<Tile.Id, ArrayList<Troop>> troopsPerTile = Maps.newHashMap();
}
