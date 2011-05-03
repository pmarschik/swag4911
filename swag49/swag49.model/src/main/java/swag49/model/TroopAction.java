package swag49.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TroopAction extends Action {
	
	@ManyToMany
	private Set<Troop> concerns = new HashSet<Troop>();

	public Set<Troop> getConcerns() {
		return concerns;
	}

	public void setConcerns(Set<Troop> concerns) {
		this.concerns = concerns;
	}
	
}
