package swag.model;

@javax.persistence.Entity
@javax.persistence.Inheritance(strategy = javax.persistence.InheritanceType.TABLE_PER_CLASS)
public abstract class Action {

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.TABLE)
	private Long id;

	@javax.persistence.Column(nullable = false)
	private java.util.Date startDate;

	@javax.persistence.Column(nullable = false)
	private Integer duration;

	@javax.persistence.ManyToOne(optional = false)
	private Tile target;

	@javax.persistence.ManyToOne(optional = false)
	private Player player;

	public Long getId() {
		return id;
	}

	public java.util.Date getStartDate() {
		return startDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public Tile getTarget() {
		return target;
	}

	public Player getPlayer() {
		return player;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public void setTarget(Tile target) {
		this.target = target;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
