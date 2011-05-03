package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopLevel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="troopLevelDAO")
public class TroopLevelDao implements DataAccessObject<TroopLevel> {

	@PersistenceContext
	private EntityManager em;

	public TroopLevelDao() {
	}

	public boolean contains(TroopLevel troopLevel)
	{
		return em.contains(troopLevel);
	}

	@Transactional
	public TroopLevel create(TroopLevel troopLevel) {
		return em.merge(troopLevel);
	}

	@Transactional
	public void delete(TroopLevel troopLevel) {
		troopLevel = em.merge(troopLevel);
		em.remove(troopLevel);
	}

	public TroopLevel get(Long id) {
		return em.find(TroopLevel.class, id);
	}

	@Transactional
	public TroopLevel update(TroopLevel troopLevel) {
		return em.merge(troopLevel);
	}
}
