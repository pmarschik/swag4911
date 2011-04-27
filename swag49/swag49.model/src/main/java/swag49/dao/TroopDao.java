package swag49.dao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Troop;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

//annotate with @Repository because it is a DAO, other components should be annotated with @Component
@Repository(value="troopDAO")
public class TroopDao implements DataAccessObject<Troop> {

	@PersistenceContext
	private EntityManager em;

	public TroopDao() {
	}

	public Troop get(Long id) {
		return em.find(Troop.class, id);
	}

	 @Transactional
	public Troop create(Troop Troop) {
		return em.merge(Troop);
	}

	 @Transactional
	public Troop update(Troop Troop) {
		return em.merge(Troop);
	}

	 @Transactional
	public void delete(Troop Troop) {
		em.remove(Troop);
	}

	public boolean contains(Long id)
	{
		return em.contains(id);
	}
}
