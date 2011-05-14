package swag49.dao;

public interface DataAccessObject<T> {

	public boolean contains(T model);
	public T create(T model);
	public void delete(T model);
	public T get(Object id);
	
	public T update(T model);
}
