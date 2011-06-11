package swag49.web.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ben
 * Date: 6/11/11
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TileDTO {
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

    public long getMapId() {
        return mapId;
    }

    public void setMapId(long mapId) {
        this.mapId = mapId;
    }

    public ArrayList<SendTroopDTO> getTroops() {
        return troops;
    }

    public void setTroops(ArrayList<SendTroopDTO> troops) {
        this.troops = troops;
    }

    int x;
    int y;
    long mapId;

    ArrayList<SendTroopDTO> troops;

    public boolean equals(TileDTO dto)
    {
        if(this.x != dto.x)
            return false;
        if(this.y != dto.y)
            return false;
        if(this.mapId != dto.mapId)
            return false;

        return true;
    }
}
