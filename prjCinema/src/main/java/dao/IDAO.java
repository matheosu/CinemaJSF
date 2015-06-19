package dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface IDAO<M> {

	public abstract EntityManager getEntityManager();

	public abstract M findById(Long id);

	public abstract List<M> getAll();

	public abstract M save(M object);

	public abstract void delete(M object);

	public abstract void begin();

	public abstract void commit();

	public abstract void rollback();

}
