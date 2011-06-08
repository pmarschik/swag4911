package swag49.statistics;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import swag49.dao.DataAccessObject;
import swag49.model.Player;
import swag49.model.Statistic;
import swag49.model.StatisticEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

public abstract class StatisticCalculatorBase implements StatisticCalculator {

     private static final int DEFAULT_LIMIT = 25;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    @Qualifier("statisticDAO")
    private DataAccessObject<Statistic, Long> statisticDAO;

    @Autowired
    @Qualifier("statisticEntryDAO")
    private DataAccessObject<StatisticEntry, StatisticEntry.Id> statisticEntryDAO;

    private Integer limit = DEFAULT_LIMIT;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    @Transactional
    public void calculate() {
        Statistic statistic = new Statistic();
        statistic.setName(getStatisticName());
        Collection<Statistic> statistics = statisticDAO.queryByExample(statistic);
        assert statistics.size() <= 1;
        if (statistics.size() == 1)
            statistic = statistics.toArray(new Statistic[statistics.size()])[0];
        else
            statistic = statisticDAO.create(statistic);

        String queryString = getRankedPlayersQuery();
        TypedQuery<Player> query = em.createQuery(queryString, Player.class);
        query.setMaxResults(limit);

        statistic.getEntries().clear();
        int ranking = 1;
        for (Player player : query.getResultList()) {
            StatisticEntry entry = new StatisticEntry(statistic, ranking);
            entry.setPlayer(player);
            entry.setScore(getScore(player));
            statisticEntryDAO.create(entry);
            ranking++;
        }

        statisticDAO.update(statistic);
    }

    protected abstract String getRankedPlayersQuery();

    protected abstract int getScore(Player player);

    protected abstract String getStatisticName();
}
