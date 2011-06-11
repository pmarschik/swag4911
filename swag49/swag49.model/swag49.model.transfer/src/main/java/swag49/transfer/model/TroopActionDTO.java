package swag49.transfer.model;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Martin
 * Date: 11.06.11
 * Time: 12:42
 * To change this template use File | Settings | File Templates.
 */
public class TroopActionDTO {
    private int destination_x;
    private int destination_y;
    private boolean isAbortable;
    private Date startDate;
    private Date endDate;

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

    public boolean getAbortable() {
        return isAbortable;
    }

    public void setAbortable(boolean abortable) {
        isAbortable = abortable;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TroopActionDTO() {
        //To change body of created methods use File | Settings | File Templates.
    }

    public void setDestionation(Integer x, Integer y) {
        this.destination_x = x;
        this.destination_y = y;
    }

    public void setIsAbortable(Boolean isAbortable) {
        this.isAbortable = isAbortable;
    }
}
