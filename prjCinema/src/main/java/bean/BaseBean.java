package bean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.BaseModel;

import org.apache.log4j.Logger;

import util.JSFUtil;
import util.PathUtil;
import dao.IDAO;

/**
 * Classe Abstrata que contém a implementação de um CRUD do Model passado por Generics;
 * <br></br>
 * <b>Notas:</b>
 * 		<br>É necessário que esse model siga o padrão JavaBeans e que implemente BaseModel;
 * 		<br>Tenha uma classe DAO seguindo do nome do model mas posfixo 'DAO';
 * 		<br>A classe DAO tem que implementar direta/indiretamente a interface IDAO;
 * 		<blockquote> Exemplo: Classe Model = Pessoa; Classe DAO = PessoaDAO; </blockquote> 
 * @author matheuscastro
 *
 * @param <M> Model a ter o CRUD implementado;
 */
public abstract class BaseBean<M extends BaseModel> implements IBean<M> {
	
	/* Logger */
    private static final Logger logger = Logger.getLogger(BaseBean.class);
   
    /* Patterns */
	private static final String PATTERN_DAO = "DAO";
	private static final String PATH_DAO = "dao.";
	private static final String PATTERN_BEAN = "Bean";

	/* Class for Reflection and DAOClass for the Class Reflection */
	private Class<M> clazz;
	private Class<? extends IDAO<M>> daoClazz;
	
	/* ActionFiles and AbsolutePath */
	protected static String action_list;
	protected static String action_edit;
	
	/* Instance and Instances */
	private List<M> instances = new ArrayList<M>();
	private M instance;
	
	/* DAO */
	protected IDAO<M> dao; 

	public BaseBean() {
		super();

		if(getClazz() != null)
			this.setInstance(newInstance());
		
		if(getDAOClazz() != null)
			this.dao = newInstanceDAO(getDAOClazz());
	}
	
	/* Getters and Setters */
	public M getInstance() {
		return instance;
	}

	public void setInstance(M instance) {
		this.instance = instance;
	}

	public List<M> getInstances() {
		if(this.instances == null || this.instances.isEmpty())
			this.instances = this.dao.getAll();
		
		return this.instances;
	}
	
	/* CRUD */
	public String list(){
		this.instances = null;
		return PathUtil.getActionList(this.getClazz(), true);
	}
	
	public String create(){
		this.setInstance(this.newInstance());
		return PathUtil.getActionEdit(this.getClazz(), true);
	}

	public String save(){
		if((this.getInstance().getId() != null) && (this.getInstance().getId().longValue() == 0))
			this.getInstance().setId(null);
		
		this.dao.save(this.getInstance());
		this.instances = null;
		
		return this.back();
	}

	public String edit(){
		Long id = JSFUtil.getParametroLong("id");
		this.setInstance(this.dao.findById(id));
		return PathUtil.getActionEdit(this.getClazz(), false);
	}
	
	public String delete(){
		Long id = JSFUtil.getParametroLong("id");
		this.dao.delete(this.dao.findById(id));
		this.instances = null;
		
		return this.back();
	}
	
	public String back(){
		this.setInstance(this.newInstance());
		return PathUtil.getActionList(this.getClazz(), true);
	}
	
	
	/**
	 * Method that create a new instance for the class pass in parameter;
	 * @param clazz Class that a create a new instance;
	 * @return T new instance for the clazz
	 * @author matheuscastro
	 */
	protected abstract M newInstance();
	
	/*
	 * ########################################################################
	 * ########################################################################
	 * ######################### PRIVATE METHODS ##############################
	 * ########################################################################
	 * ########################################################################
	 */
	private IDAO<M> newInstanceDAO(Class<? extends IDAO<M>> clazzDAO){
		try{
			return clazzDAO.newInstance();
		} catch (InstantiationException ie) {
			logger.error("Error in create a newInstanceDAO for the " + clazzDAO.getSimpleName() + ": ", ie);
		} catch (IllegalAccessException iae) {
			logger.error("Error in create a newInstanceDAO for the " + clazzDAO.getSimpleName() + ": ", iae);
		} catch (Exception e){
			logger.error("Some error is occurred: ", e);
		}
		return null;
	}
	
	/**
	 * Método que descobre qual é a classe por reflection no generics
	 * @return Class
	 * @author matheuscastro
	 * @author mauro
	 */
	@SuppressWarnings("unchecked")
	private Class<M> getClazz() {
		if(this.clazz == null){
			Type tipo = ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			this.clazz = (Class<M>) tipo;
		}
		return this.clazz;
	}

	/**
	 * Método que devolve a Classe DAO da classe que foi passada por reflection no generics;
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	private Class<? extends IDAO<M>> getDAOClazz(){
		if(this.daoClazz == null){
			try {
				this.daoClazz = (Class<? extends IDAO<M>>) Class.forName(PATH_DAO + this.getClazz().getSimpleName() + PATTERN_DAO);
			} catch (ClassNotFoundException cnfE) {
				logger.error("DAO not found for this class" + this.getClazz().getSimpleName() + ": ", cnfE);
			}	
		}
		return this.daoClazz;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((instance == null) ? 0 : instance.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseBean<?> other = (BaseBean<?>) obj;
		if (instance == null) {
			if (other.instance != null)
				return false;
		} else if (!instance.equals(other.instance))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClazz().getSimpleName() + PATTERN_BEAN + " [instance=" + instance + "]";
	}
	
}
