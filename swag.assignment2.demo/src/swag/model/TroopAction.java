package swag.model;

@javax.persistence.Entity
public class TroopAction extends Action {

	@javax.persistence.ManyToMany()
	private java.util.Set<Troop> concerns = new java.util.HashSet<Troop>();

	public TroopAction() {
	}

	public java.util.Set<Troop> getConcerns() {
		return concerns;
	}

	public void setConcerns(java.util.Set<Troop> concerns) {
		this.concerns = concerns;
	}

}
