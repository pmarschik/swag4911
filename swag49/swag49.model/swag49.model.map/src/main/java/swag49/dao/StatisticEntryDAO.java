package swag49.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.StatisticEntry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("statisticEntryDAO")
@Transactional("swag49.map")
public class StatisticEntryDAO extends AbstractDataAccessObject<StatisticEntry, StatisticEntry.Id> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public StatisticEntryDAO() {
        super(StatisticEntry.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
