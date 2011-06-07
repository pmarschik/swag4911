package swag49.dao;

import java.util.List;

public interface DataAccessObject<T> {

	public boolean contains(T model);

	public T create(T model);

	public void delete(T model);

	public T get(Object id);

	public List<T> queryByExample(T model);

	public T update(T model);
}
