package swag49.model;

import swag49.listener.TroopActionListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners({TroopActionListener.class})
public class TroopAction extends Action {

    @ManyToMany
    private Set<Troop> concerns = new HashSet<Troop>();

    private Boolean shouldFoundBase;

    @ManyToOne(optional = false)
    private Tile source;

    public Tile getSource() {
        return source;
    }

    public void setSource(Tile source) {
        this.source = source;
    }

    public Set<Troop> getConcerns() {
        return concerns;
    }

    public void setConcerns(Set<Troop> concerns) {
        this.concerns = concerns;
    }

    public Boolean getShouldFoundBase() {
        return shouldFoundBase;
    }

    public void setShouldFoundBase(Boolean shouldFoundBase) {
        this.shouldFoundBase = shouldFoundBase;
    }

}
