package swag49.web.model;

import com.google.common.collect.Maps;
import swag49.model.Tile;
import swag49.model.Troop;

import java.util.ArrayList;
import java.util.HashMap;

public class TroopsPerTileDTO {
    int x;

    public boolean isFoundBase() {
        return foundBase;
    }

    public void setFoundBase(boolean foundBase) {
        this.foundBase = foundBase;
    }

    boolean foundBase = false;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    int y;
    public ArrayList<TileDTO> getTileList() {
        return tileList;
    }

    public void setTileList(ArrayList<TileDTO> tileList) {
        this.tileList = tileList;
    }

    ArrayList<TileDTO> tileList = new ArrayList<TileDTO>();
}
