package swag.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class TroopAction extends Action {
	
	@ManyToMany
	private Set<Troop> concerns = new HashSet<Troop>();

	public void setConcerns(Set<Troop> concerns) {
		this.concerns = concerns;
	}

	public Set<Troop> getConcerns() {
		return concerns;
	}
	
}
