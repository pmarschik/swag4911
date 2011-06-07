package swag49.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import swag49.model.Troop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//annotate with @Repository because it is a DAO, other components should be annotated with @Component
@Repository(value = "troopDAO")
public class TroopDao implements DataAccessObject<Troop> {

	@PersistenceContext
	private EntityManager em;

	public TroopDao() {
	}

	public boolean contains(Troop troop) {
		return em.contains(troop);
	}

	@Transactional
	public Troop create(Troop troop) {
		return em.merge(troop);
	}

	@Transactional
	public void delete(Troop troop) {
		troop = em.merge(troop);
		em.remove(troop);
	}

	public Troop get(Object id) {
		return em.find(Troop.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Troop> queryByExample(Troop model) {
		Session session = (Session) em.getDelegate();
		Criteria criteria = session.createCriteria(Troop.class);

		if (model != null)
			criteria.add(Example.create(model));

		return criteria.list();
	}

	@Transactional
	public Troop update(Troop troop) {
		return em.merge(troop);
	}

}
