package bean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.BaseModel;

import org.apache.log4j.Logger;

import util.JSFUtil;
import dao.IDAO;

/**
 * Classe Abstrata que contém a implementação de um CRUD do Model passado por Generics;
 * <br></br>
 * <b>Notas:</b>
 * 		<br>É necessário que esse model siga o padrão JavaBeans;
 * 		<br>Tenha uma classe DAO seguindo do nome do model mas posfixo 'DAO';
 * 		<br>A classe DAO tem que implementar direta/indiretamente a interface IDAO;
 * 		<blockquote> Exemplo: Classe Model = Pessoa; Classe DAO = PessoaDAO; </blockquote> 
 * @author matheuscastro
 *
 * @param <T> Model a ter o CRUD implementado;
 */
public abstract class BaseBean<T extends BaseModel> {
	
	/**  Begin Constants **/

	/* Logger */
    protected final Logger logger = Logger.getLogger(this.getClazz().getClass());
   
    /* Patterns */
	private static final String PATTERN_ACTION_LIST = "s.action";
	private static final String PATTERN_ACTION_EDIT = "_edit.action";
	private static final String PATTERN_DAO = "DAO";
	private static final String PATH_DAO = "dao.";

	/* Paths */
	private static final String RAIZ_PATH = "/";
	private static final String RESTRICT_PATH = RAIZ_PATH + "restrito/";
	private static final String CONTROLE_PATH = RESTRICT_PATH + "controle/";
	private static final String OPERACAO_PATH = RESTRICT_PATH + "operacao/";
	private static final String ADMINISRACAO_PATH = RESTRICT_PATH + "administracao/";
	
	/* Redirect */
	private static final String REDIRECT = "?faces-redirect=true";

	/* Class Controller */
	private static final String[] CLASSES_CONTROLE = {"filme","genero"};
	private static final String[] CLASSES_OPERACAO = {"sala","sessao"};
	private static final String[] CLASSES_ADMINISTRACAO = {"funcionario","setor"};

	/* Flash Scoped */
	public final String FLASH_INSTANCE = "instance" + this.getClass().getSimpleName();
	
	/** End Constants **/
	
	/* Class for Reflection and DAOClass for the Class Reflection */
	private Class<T> clazz;
	private Class<? extends IDAO<T>> daoClazz;
	
	/* ActionFiles and AbsolutePath */
	protected static String action_list;
	protected static String action_edit;
	protected String absolutePath;
	
	/* Instance and Instances */
	private List<T> instances = new ArrayList<T>();
	private T instance;
	
	/* DAO */
	private IDAO<T> dao; 

	public BaseBean() {
		super();
		this.initPath();
		
		if(getClazz() != null)
			this.setInstance(newInstance());
		
		if(getDAOClazz() != null)
			this.dao = newInstanceDAO(getDAOClazz());
	}
	
	/* Getters and Setters */
	public T getInstance() {
		return instance;
	}

	public void setInstance(T instance) {
		this.instance = instance;
	}

	public List<T> getInstances() {
		if(this.instances == null || this.instances.isEmpty())
			this.instances = this.dao.getAll();
		
		return this.instances;
	}
	
	/** Show Static URL 
	 * Retorna o endereço completo do XHTML de List do Bean junto com o REDIRECT!!!
	 * 
	 * @return o endereço completo do XHTML;
	 * @author matheuscastro
	 * */
	public static String show(){
		return action_list + REDIRECT;
	}

	/* CRUD */
	public String list(){
		return action_list + REDIRECT;
	}
	
	public String create(){
		this.setInstance(this.newInstance());
		return action_edit + REDIRECT;
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
		return action_edit;
	}
	
	public String delete(){
		Long id = JSFUtil.getParametroLong("id");
		this.dao.delete(this.dao.findById(id));
		this.instances = null;
		
		return this.back();
	}
	
	public String back(){
		this.setInstance(this.newInstance());
		return action_list + REDIRECT;
	}
	
	/*
	 * ########################################################################
	 * ########################################################################
	 * ######################### PRIVATE METHODS ##############################
	 * ########################################################################
	 * ########################################################################
	 */
	
	/**
	 * Method that a initialize all paths for the actual class;
	 */
	private void initPath(){
		this.absolutePath = this.getAbsolutePath();
		action_list = this.absolutePath + this.getClazz().getSimpleName().toLowerCase() + PATTERN_ACTION_LIST;
		action_edit = this.absolutePath + this.getClazz().getSimpleName().toLowerCase() + PATTERN_ACTION_EDIT;
	}
	
	/**
	 * Method that create a new instance for the class pass in parameter;
	 * @param clazz Class that a create a new instance;
	 * @return T new instance for the clazz
	 * @author matheuscastro
	 * @return 
	 */
	protected abstract T newInstance();
	
	private IDAO<T> newInstanceDAO(Class<? extends IDAO<T>> clazzDAO){
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
	 * Método que determina o caminho absoluto de onde estará os actions de
	 * acordo com a classe
	 * 
	 * @return String com o caminho absoluto
	 */
	private String getAbsolutePath(){
		String classLower = this.getClazz().getSimpleName().toLowerCase();
		
		for(String clazz : CLASSES_CONTROLE){
			if(clazz.equals(classLower)){
				return CONTROLE_PATH + classLower + "/";
			}
		}
		
		for(String clazz : CLASSES_OPERACAO){
			if(clazz.equals(classLower)){
				return OPERACAO_PATH + classLower + "/";
			}
		}
		
		for(String clazz : CLASSES_ADMINISTRACAO){
			if(clazz.equals(classLower)){
				return ADMINISRACAO_PATH + classLower + "/";
			}
		}
		
		return RESTRICT_PATH;
	}
	
	/**
	 * Método que descobre qual é a classe por reflection no generics
	 * @return Class
	 * @author matheuscastro
	 * @author mauro
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getClazz() {
		if(this.clazz == null){
			Type tipo = ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			this.clazz = (Class<T>) tipo;
		}
		return this.clazz;
	}

	/**
	 * Método que devolve a Classe DAO da classe que foi passada por reflection no generics;
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	private Class<? extends IDAO<T>> getDAOClazz(){
		if(this.daoClazz == null){
			try {
				this.daoClazz = (Class<? extends IDAO<T>>) Class.forName(PATH_DAO + this.getClazz().getSimpleName() + PATTERN_DAO);
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
		return "BaseBean [instance=" + instance + "]";
	}
	
}
