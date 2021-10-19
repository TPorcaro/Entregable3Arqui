package repositories;

public interface RepositoryInterface<T,K> {

	void create(T entity);
	boolean remove(K key);
	boolean update(T entity);
	T get(K key);
}
