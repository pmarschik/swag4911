package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("playerDAO")
public class PlayerDAO implements DataAccessObject<Player, Long> {

    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    @Transactional("swag49.map")
    public boolean contains(Player player) {
        return em.contains(player);
    }

    @Transactional("swag49.map")
    public Player create(Player player) {
        return em.merge(player);
    }

    @Transactional("swag49.map")
    public void delete(Player player) {
        player = em.merge(player);
        em.remove(player);
    }

    @Transactional("swag49.map")
    public Player get(Long id) {
        return em.find(Player.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional("swag49.map")
    public List<Player> queryByExample(Player model) {
        Session session = (Session) em.getDelegate();
        Criteria criteria = session.createCriteria(Player.class);

        if (model != null)
            criteria.add(Example.create(model));

        return criteria.list();
    }

    @Transactional("swag49.map")
    public Player update(Player player) {
        return em.merge(player);
    }
}
