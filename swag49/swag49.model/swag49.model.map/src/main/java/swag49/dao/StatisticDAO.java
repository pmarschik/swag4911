package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Statistic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("statisticDAO")
@Transactional("swag49.map")
public class StatisticDAO extends AbstractDataAccessObject<Statistic, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public StatisticDAO() {
        super(Statistic.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
