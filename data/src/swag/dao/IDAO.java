package swag.dao;

public interface IDao<T> {

	public T get(Long id);
	public void create(T model);
	public void update(T model);
	public void delete(T model);
	
	public boolean contains(Long id);
}
