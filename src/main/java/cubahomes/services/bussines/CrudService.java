package cubahomes.services.bussines;

import java.util.List;

public interface CrudService<T> {

	public T save(T object);
	public List<T> findAll();
	public T findById(long id);
	public void delete(T object);
	public void deleteById(long id);
	
}
