package swag49.dao;

import java.util.Collection;

public interface DataAccessObject<T> {

	public boolean contains(T model);

	public T create(T model);

	public void delete(T model);

	public T get(Object id);

	public Collection<T> queryByExample(T model);

	public T update(T model);
}
