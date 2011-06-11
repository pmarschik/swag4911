package swag49.dao;

import java.util.List;

public interface DataAccessObject<T, PK> {

	public boolean contains(T model);

	public T create(T model);

	public void delete(T model);

	public T get(PK id);

	public List<T> queryByExample(T model);

	public T update(T model);

    public void detach(T model);
}
