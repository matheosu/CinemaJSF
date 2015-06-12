package dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface IDAO<T> {

	public abstract EntityManager getEntityManager();

	public abstract T findById(Long id);

	public abstract List<T> getAll();

	public abstract T save(T object);

	public abstract void delete(T object);

	public abstract void begin();

	public abstract void commit();

	public abstract void rollback();

}
