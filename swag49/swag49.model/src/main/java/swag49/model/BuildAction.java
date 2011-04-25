package swag49.model;

import javax.persistence.ManyToOne;

public class BuildAction extends Action {

	@ManyToOne(optional=false)
	private Building concerns;

	public void setConcerns(Building concerns) {
		this.concerns = concerns;
	}

	public Building getConcerns() {
		return concerns;
	}
}
