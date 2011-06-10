package swag49.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class TroopLevel extends LevelBase {


    @Embeddable
    public static class Id implements Serializable {

        private static final long serialVersionUID = 1L;
        private Integer level;
        private Long troopTypeId;

        public Id() {
            super();
        }

        public Id(int level, long troopTypeId) {
            this.level = level;
            this.troopTypeId = troopTypeId;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof Id) {
                return this.level.equals(((Id) obj).level)
                        && this.troopTypeId.equals(((Id) obj).troopTypeId);
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return level.hashCode() + troopTypeId.hashCode();
        }

        public Integer getLevel() {
            return level;
        }

        public void setLevel(Integer level) {
            this.level = level;
        }

        public Long getTroopTypeId() {
            return troopTypeId;
        }

        public void setTroopTypeId(Long troopTypeId) {
            this.troopTypeId = troopTypeId;
        }
    }

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne(optional = false)
    @JoinColumn(name = "troopTypeId", insertable = false, updatable = false)
    private TroopType troopType;

    @Column(nullable = false)
    private Integer strength;

    @Column(nullable = false)
    private Integer speed;

    @Column(nullable = false)
    private Integer defense;

    @Column(nullable = false)
    private Integer cargo_capacity;

    public Integer getCargo_capacity() {
        return cargo_capacity;
    }

    public void setCargo_capacity(Integer cargo_capacity) {
        this.cargo_capacity = cargo_capacity;
    }

    public TroopLevel() {
    }

    public TroopLevel(TroopType troopType, int level) {
        this.setTroopType(troopType);

        this.id.troopTypeId = troopType.getId();
        this.id.level = level;

        this.getTroopType().getLevels().add(this);
    }

    public Integer getDefense() {
        return defense;
    }

    public Id getId() {
        return id;
    }

    @Override
    public Integer getLevel() {
        return id.level;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getStrength() {
        return strength;
    }

    public TroopType getTroopType() {
        return troopType;
    }

    public void setDefense(Integer defense) {
        this.defense = defense;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public void setTroopType(TroopType troopType) {
        this.troopType = troopType;
    }

}
