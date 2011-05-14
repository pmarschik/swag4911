package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.TroopType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository(value="troopTypeDAO")
public class TroopTypeDao implements DataAccessObject<TroopType> {

	@PersistenceContext
	private EntityManager em;

	public TroopTypeDao() {
	}

	public boolean contains(TroopType troopType)
	{
		return em.contains(troopType);
	}

	@Transactional
	public TroopType create(TroopType troopType) {
		return em.merge(troopType);
	}

	@Transactional
	public void delete(TroopType troopType) {
		troopType = em.merge(troopType);
		em.remove(troopType);
	}

	public TroopType get(Object id) {
		return em.find(TroopType.class, id);
	}

	@Transactional
	public TroopType update(TroopType troopType) {
		return em.merge(troopType);
	}
}
