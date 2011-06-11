package swag49.transfer.model;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 11.06.11
 * Time: 12:42
 * To change this template use File | Settings | File Templates.
 */
public class BuildActionDTO {
    private String buildingName;
    private int destination_x;
    private int destination_y;
    private boolean isAbortable;
    private int squareId;
    private int level;
    private Date endDate;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getDestination_x() {
        return destination_x;
    }

    public void setDestination_x(int destination_x) {
        this.destination_x = destination_x;
    }

    public int getDestination_y() {
        return destination_y;
    }

    public void setDestination_y(int destination_y) {
        this.destination_y = destination_y;
    }

    public boolean isAbortable() {
        return isAbortable;
    }

    public void setAbortable(boolean abortable) {
        isAbortable = abortable;
    }

    public int getSquareId() {
        return squareId;
    }

    public void setSquareId(int squareId) {
        this.squareId = squareId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean getIsAbortable() {
        return isAbortable;
    }

    public void setIsAbortable(boolean abortable) {
        isAbortable = abortable;
    }
}
