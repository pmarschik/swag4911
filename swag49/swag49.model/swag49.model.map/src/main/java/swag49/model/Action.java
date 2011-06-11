package swag49.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swag49.model.listener.ActionPersistenceEventEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@EntityListeners(ActionPersistenceEventEntityListener.class)
public abstract class Action {

    protected static final Logger logger = LoggerFactory.getLogger(Action.class);

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(nullable = false)
    private Long duration;

    @ManyToOne(optional = false)
    private Tile target;

    @ManyToOne(optional = false)
    private Player player;

    private Boolean isAbortable;

    public Boolean getIsAbortable() {
        return isAbortable;
    }

    public void setIsAbortable(Boolean isAbortable) {
        this.isAbortable = isAbortable;
    }

    public Long getDuration() {
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

    public void setDuration(Long duration) {
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
