package swag49.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BuildAction extends Action {

    @ManyToOne(optional = false)
    private Building concerns;

    public Building getConcerns() {
        return concerns;
    }

    public void setConcerns(Building concerns) {
        this.concerns = concerns;
    }
}
