package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.BuildAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("buildActionDAO")
public class BuildActionDAO implements DataAccessObject<BuildAction, Long> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(BuildAction buildAction) {
        return em.contains(buildAction);
    }

    @Transactional("swag49.map")
    public BuildAction create(BuildAction buildAction) {
        return em.merge(buildAction);
    }

    @Transactional("swag49.map")
    public void delete(BuildAction buildAction) {
        buildAction = em.merge(buildAction);
        em.remove(buildAction);
    }

    @Transactional("swag49.map")
    public BuildAction get(Long id) {
        return em.find(BuildAction.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<BuildAction> queryByExample(BuildAction model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(BuildAction.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public BuildAction update(BuildAction buildAction) {
        return em.merge(buildAction);
    }

}
