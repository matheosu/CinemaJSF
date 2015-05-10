package dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface IDAO<T> {

	public EntityManager getEntityManager();

	public T findById(Long id);

	public List<T> getAll();

	public T save(T object);

	public void delete(T object);

	public void begin();

	public void commit();

	public void rollback();

}
