package swag49.statistics;

import org.quartz.Job;
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

public class MostBasesEvaluatorJob implements Job {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    @Qualifier("statisticDAO")
    private DataAccessObject<Statistic> statisticDAO;

    @Override
    @Transactional
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Statistic statistic = new Statistic();
        statistic.setName("Most bases");
        Collection<Statistic> statistics = statisticDAO.queryByExample(statistic);
        assert statistics.size() <= 1;
        if (statistics.size() == 1)
            statistic = statistics.toArray(new Statistic[statistics.size()])[0];

        String queryString = "select player from Player player order by player.owns.size desc";
        TypedQuery<Player> query = em.createQuery(queryString, Player.class);
        query.setMaxResults(25);

        statistic.getEntries().clear();
        int ranking = 1;
        for (Player player : query.getResultList()) {
            StatisticEntry entry = new StatisticEntry(statistic, ranking);
            entry.setPlayer(player);
        }

        statisticDAO.update(statistic);
    }
}
