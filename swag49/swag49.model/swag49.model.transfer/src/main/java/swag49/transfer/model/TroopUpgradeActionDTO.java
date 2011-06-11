package swag49.transfer.model;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 11.06.11
 * Time: 12:42
 * To change this template use File | Settings | File Templates.
 */
public class TroopUpgradeActionDTO {
    private String troopName;

    private int destination_x;
    private int destination_y;
    private boolean isAbortable;
    private Date endDate;
    private int level;

    public String getTroopName() {
        return troopName;
    }

    public void setTroopName(String troopName) {
        this.troopName = troopName;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
