package swag49.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(nullable = false)
	private Date startDate;

	@Column(nullable = false)
	private Integer duration;

	@ManyToOne(optional = false)
	private Tile target;

	@ManyToOne(optional = false)
	private Player player;

	public Integer getDuration() {
		return duration;
	}

	public Long getId() {
		return id;
	}

	public Player getPlayer() {
		return player;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Tile getTarget() {
		return target;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setTarget(Tile target) {
		this.target = target;
	}
	
	public Date getEndDate() {
		if (startDate == null || duration == null)
			return null;
		
		return new Date(startDate.getTime() + duration);
	}
}
