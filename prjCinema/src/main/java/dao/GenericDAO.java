package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import util.JPAUtil;

public abstract class GenericDAO<T> implements IDAO<T> {

	protected final Logger logger = LogManager.getLogger(this.getPersistenceClass().getClass());
	
	private Class<T> persistentClass;
	private EntityManager manager;

	public GenericDAO() {
		super();
		
		this.getPersistenceClass();
		this.manager = JPAUtil.getEntityManager();
	}
	
	public GenericDAO(EntityManager manager){
		this();
		this.manager = manager;
	}

	@Override
	public EntityManager getEntityManager() {
		if ((this.manager != null) && (!this.manager.isOpen()))
			this.manager = JPAUtil.getEntityManager();

		return this.manager;
	}

	@Override
	public T findById(Long id) {
		return this.getEntityManager().find(getPersistenceClass(), id);
	}

	@Override
	public List<T> getAll() {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> c = cb.createQuery(getPersistenceClass());
		c.select(c.from(getPersistenceClass()));

		try {
			return this.getEntityManager().createQuery(c).getResultList();
		} catch (NoResultException nrE) {
			logger.error("Sem Resultados: " ,nrE);
			return null;
		} catch (Exception e){
			logger.error("Ocorreu um erro ao recuperar todos os registros de " + this.getPersistenceClass().getSimpleName() +" : ",e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public T save(T object) {

		boolean transacaoAtiva = this.getEntityManager().getTransaction().isActive();

		if (!transacaoAtiva)
			this.begin();

		this.getEntityManager().merge(object);

		if (!transacaoAtiva)
			this.commit();

		return object;
	}

	@Override
	public void delete(T object) {
		boolean transacaoAtiva = this.getEntityManager().getTransaction().isActive();

		if (!transacaoAtiva)
			this.begin();

		this.getEntityManager().remove(object);

		if (!transacaoAtiva)
			this.commit();
	}

	@Override
	public void begin() {
		this.getEntityManager().getTransaction().begin();
	}

	@Override
	public void commit() {
		this.getEntityManager().flush();
		this.getEntityManager().getTransaction().commit();
	}

	@Override
	public void rollback() {
		this.getEntityManager().getTransaction().rollback();
	}

	
	@SuppressWarnings("unchecked")
	public Class<T> getPersistenceClass(){
		if(this.persistentClass == null){
			Type tipo = ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			this.persistentClass = (Class<T>) tipo;
		}
		return this.persistentClass;
	}
}
