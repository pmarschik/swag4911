package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopAction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="troopActionDAO")
public class TroopActionDao implements DataAccessObject<TroopAction> {

	@PersistenceContext
	private EntityManager em;

	public TroopActionDao() {
	}

	public boolean contains(TroopAction troopAction)
	{
		return em.contains(troopAction);
	}

	@Transactional
	public TroopAction create(TroopAction troopAction) {
		return em.merge(troopAction);
	}

	@Transactional
	public void delete(TroopAction troopAction) {
		troopAction = em.merge(troopAction);
		em.remove(troopAction);
	}

	public TroopAction get(Object id) {
		return em.find(TroopAction.class, id);
	}

	
	@Transactional
	public TroopAction update(TroopAction troopAction) {
		return em.merge(troopAction);
	}
}

