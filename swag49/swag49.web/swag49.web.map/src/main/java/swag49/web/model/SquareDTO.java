package swag49.web.model;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 12.06.11
 * Time: 01:30
 * To change this template use File | Settings | File Templates.
 */
public class SquareDTO {
    private BuildingDTO building;
    private long baseId;
    private int position;


    public BuildingDTO getBuilding() {
        return building;
    }

    public void setBuilding(BuildingDTO building) {
        this.building = building;
    }

    public long getBaseId() {
        return baseId;
    }

    public void setBaseId(long baseId) {
        this.baseId = baseId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
