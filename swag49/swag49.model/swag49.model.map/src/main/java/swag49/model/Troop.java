package swag49.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Troop {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private TroopType type;

    @ManyToOne
    private Tile position;

    @ManyToOne(optional = false)
    private TroopLevel isOfLevel;

    @ManyToOne(optional = false)
    private Player owner;

    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Troop() {
    }


    public Troop(TroopType type, TroopLevel level, Tile position, Player owner) {
        this.type = type;
        this.position = position;
        this.owner = owner;
        this.isOfLevel = level;
        this.active = Boolean.TRUE;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public TroopLevel getIsOfLevel() {
        return isOfLevel;
    }

    public Tile getPosition() {
        return position;
    }

    public TroopType getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsOfLevel(TroopLevel isOfLevel) {
        this.isOfLevel = isOfLevel;
    }

    public void setPosition(Tile position) {
        this.position = position;
    }

    public void setType(TroopType type) {
        this.type = type;
    }

}
