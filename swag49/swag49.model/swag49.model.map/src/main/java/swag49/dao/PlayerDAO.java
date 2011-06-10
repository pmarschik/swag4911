package swag49.dao;

import org.springframework.stereotype.Repository;
import swag49.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("playerDAO")
public class PlayerDAO extends AbstractDataAccessObject<Player, Long> {
    @PersistenceContext(unitName = "swag49.map")
    private EntityManager em;

    public PlayerDAO() {
        super(Player.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
