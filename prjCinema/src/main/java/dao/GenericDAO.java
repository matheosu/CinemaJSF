package dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import model.BaseModel;

import org.apache.log4j.Logger;

import util.JPAUtil;

public abstract class GenericDAO<M extends BaseModel> implements IDAO<M> {

	private static final Logger logger = Logger.getLogger(GenericDAO.class);
	
	private Class<M> persistentClass;
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
	public M findById(Long id) {
		try {
			return this.getEntityManager().find(getPersistenceClass(), id);
		} catch (PersistenceException pe) {
			logger.error("Deu ruim " + pe.getMessage() + " : ",pe);
		}
		return null;
	}

	@Override
	public List<M> getAll() {
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<M> c = cb.createQuery(getPersistenceClass());
		c.select(c.from(getPersistenceClass()));

		try {
			return this.getEntityManager().createQuery(c).getResultList();
		} catch (NoResultException nrE) {
			logger.warn("Sem Resultados: " ,nrE);
			return null;
		} catch (Exception e){
			logger.error("Ocorreu um erro ao recuperar todos os registros de " + this.getPersistenceClass().getSimpleName() +" : ",e);
			return null;
		}
	}

	@Override
	public M save(M object) {

		boolean transacaoAtiva = this.getEntityManager().getTransaction().isActive();

		if (!transacaoAtiva)
			this.begin();

		
		object = this.getEntityManager().merge(object);

		if (!transacaoAtiva)
			this.commit();

		return object;
	}

	@Override
	public void delete(M object) {
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
	public Class<M> getPersistenceClass(){
		if(this.persistentClass == null){
			Type tipo = ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			this.persistentClass = (Class<M>) tipo;
		}
		return this.persistentClass;
	}
}
