package swag49.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StatisticEntry {

    @SuppressWarnings({"JpaObjectClassSignatureInspection"})
    @Embeddable
	public static class Id implements Serializable {

		private static final long serialVersionUID = 1L;
		private Long statisticId;
		private Integer ranking;

		public Id() {
			super();
		}

		public Id(long statisticId, int ranking) {
			super();
			this.statisticId = statisticId;
			this.ranking = ranking;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj != null && obj instanceof Id) {
				return this.statisticId.equals(((Id) obj).statisticId)
						&& this.ranking.equals(((Id) obj).ranking);
			} else {
				return false;
			}
		}

		@Override
		public int hashCode() {
			return statisticId.hashCode() + ranking.hashCode();
		}
	}

    @EmbeddedId
    private Id id = new Id();

    @SuppressWarnings({"JpaDataSourceORMInspection"})
    @ManyToOne(optional = false)
	@JoinColumn(name = "statisticId", insertable = false, updatable = false)
	private Statistic statistic;

    @ManyToOne(optional = false)
    private Player player;

    @Column(nullable = false)
    private Integer score;

    public StatisticEntry() {
	}

	public StatisticEntry(Statistic statistic, int ranking) {
		this.statistic = statistic;
		this.id.statisticId = statistic.getId();
		this.id.ranking = ranking;

		statistic.getEntries().add(this);
	}

    public Id getId() {
        return id;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public Integer getRanking() {
        return id.ranking;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
