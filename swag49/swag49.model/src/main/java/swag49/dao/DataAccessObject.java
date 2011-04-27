package swag49.dao;

public interface DataAccessObject<T> {

	public T get(Long id);
	public T create(T model);
	public T update(T model);
	public void delete(T model);
	
	public boolean contains(T model);
}
