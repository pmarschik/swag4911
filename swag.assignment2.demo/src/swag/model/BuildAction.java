package swag.model;

@javax.persistence.Entity
public class BuildAction extends Action {

	@javax.persistence.Embedded
	private Building concerns;

	public BuildAction() {
	}

	public Building getConcerns() {
		return concerns;
	}

	public void setConcerns(Building concerns) {
		this.concerns = concerns;
	}

}
