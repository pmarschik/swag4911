package swag49.model;

import javax.persistence.*;

@Entity
public class Building {

    @EmbeddedId
    private Square.Id id = new Square.Id();

    @OneToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumns(value = {
            @JoinColumn(name = "baseId", insertable = false, updatable = false),
            @JoinColumn(name = "position", insertable = false, updatable = false)})
    private Square square;

    @ManyToOne(optional = false)
    private BuildingType type;

    @ManyToOne(optional = false)
    private BuildingLevel isOfLevel;

    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Building() {
    }

    public Building(Square square) {
        this.square = square;
        this.id = new Square.Id(square.getBase().getId(), square.getPosition());

        //	square.setBuilding(this);
    }

    public Square.Id getId() {
        return id;
    }

    public BuildingLevel getIsOfLevel() {
        return isOfLevel;
    }

    public Square getSquare() {
        return square;
    }

    public BuildingType getType() {
        return type;
    }

    public void setIsOfLevel(BuildingLevel isOfLevel) {
        this.isOfLevel = isOfLevel;
    }

    public void setType(BuildingType type) {
        this.type = type;
    }
}
