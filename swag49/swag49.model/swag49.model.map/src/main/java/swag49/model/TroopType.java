package swag49.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TroopType {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "troopType")
    private Set<TroopLevel> levels = new HashSet<TroopLevel>();

    @Column(nullable = false)
    private Boolean canFoundBase;

    public Long getId() {
        return id;
    }

    public Set<TroopLevel> getLevels() {
        return levels;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLevels(Set<TroopLevel> levels) {
        this.levels = levels;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCanFoundBase() {
        return canFoundBase;
    }

    public void setCanFoundBase(Boolean canFoundBase) {
        this.canFoundBase = canFoundBase;
    }

}
