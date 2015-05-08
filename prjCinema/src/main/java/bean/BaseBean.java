package bean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import model.BaseModel;

import org.apache.log4j.Logger;

import util.JSFUtil;
import dao.IDAO;

public abstract class BaseBean<T extends BaseModel> {

	/* Logger */
    protected final Logger logger = Logger.getLogger(this.getPersistenceClass().getClass());
    
    /* Patterns */
	private static final String PATTERN_ACTION_LIST = "s.action";
	private static final String PATTERN_ACTION_EDIT = "_edit.action";
	private static final String PATTERN_DAO = "DAO";
	private static final String PATH_DAO = "dao.";

	/* Folders */
	private static final String RAIZ_PATH = "/";
	private static final String RESTRICT_PATH = RAIZ_PATH + "restrito/";
	private static final String CONTROLE_PATH = RESTRICT_PATH + "controle/";
	private static final String OPERACAO_PATH = RESTRICT_PATH + "operacao/";
	private static final String ADMINISRACAO_PATH = RESTRICT_PATH + "administracao/";
	
	/* Classes */
	private static final String[] CLASSES_CONTROLE = {"filme","genero"};
	private static final String[] CLASSES_OPERACAO = {"sala","sessao"};
	private static final String[] CLASSES_ADMINISTRACAO = {"funcionario","setor"};
	
	/* Redirect */
	private static final String REDIRECT = "?faces-redirect=true";
	
	/* Classes */
	private Class<BaseModel> persistentClass;
	private Class<?> daoClass;
	
	/* ActionFiles and AbsolutePath */
	protected String action_list;
	protected String action_edit;
	protected String absolutePath;
	
	/* Instance and Instances */
	private List<BaseModel> instances = new ArrayList<BaseModel>();
	private BaseModel instance;
	
	/* DAO */
	private IDAO<BaseModel> dao; 

	@SuppressWarnings("unchecked")
	public BaseBean(){
		super();
		
		this.absolutePath = this.getAbsolutePath();
		this.action_list = this.absolutePath + this.getPersistenceClass().getSimpleName().toLowerCase() + PATTERN_ACTION_LIST;
		this.action_edit = this.absolutePath + this.getPersistenceClass().getSimpleName().toLowerCase() + PATTERN_ACTION_EDIT;
		
		try {
			this.daoClass = Class.forName(PATH_DAO + this.getPersistenceClass().getSimpleName() + PATTERN_DAO);
			this.dao = (IDAO<BaseModel>) daoClass.newInstance();
		} catch (ClassNotFoundException cnfE) {
			logger.error("DAO not found for this class" + this.getPersistenceClass().getSimpleName() + ": " + cnfE.getMessage());
		} catch (InstantiationException iE) {
			logger.error("Error in create a newInstance for DAO: " + iE.getMessage());
		} catch (IllegalAccessException iaE) {
			logger.error("Error in create a newInstance for DAO: " + iaE.getMessage());
		}
		
	}
	
	/**
	 * Método que descobre qual é a classe por reflection do generics
	 * @return Classe 
	 * @author matheuscastro
	 * @author mauro
	 */
	@SuppressWarnings("unchecked")
	private Class<BaseModel> getPersistenceClass() {
		if(this.persistentClass == null){
			Type tipo = ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			this.persistentClass = (Class<BaseModel>) tipo;
		}
		return this.persistentClass;
	}

	public BaseModel getInstance() {
		return instance;
	}

	public void setInstance(BaseModel instance) {
		this.instance = instance;
	}

	public List<BaseModel> getInstances() {
		if(this.instances == null || this.instances.isEmpty())
			this.instances = this.dao.getAll();
		
		return this.instances;
	}

	public String list(){
		logger.info("Testando");
		return this.action_list + REDIRECT;
	}
	
	public String create(){
		this.newInstance();
		return this.action_edit + REDIRECT;
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
		
		return this.action_edit;
	}
	
	public String delete(){
		Long id = JSFUtil.getParametroLong("id");
		this.dao.delete(this.dao.findById(id));
		this.instances = null;
		
		return this.back();
	}
	
	public String back(){
		this.newInstance();
		return this.action_list + REDIRECT;
	}
	
	private boolean newInstance(){
		try {
			this.setInstance(this.getPersistenceClass().newInstance());
			return true;
		} catch (InstantiationException iE) {
			logger.error("Error in create a newInstance for the " + this.getPersistenceClass().getSimpleName() + ": " + iE.getMessage());
			return false;
		} catch (IllegalAccessException iaE) {
			logger.error("Error in create a newInstance for the " + this.getPersistenceClass().getSimpleName() + ": "+ iaE.getMessage());
			return false;
		}
	}
	
	private String getAbsolutePath(){
		String classLower = this.getPersistenceClass().getSimpleName().toLowerCase();
		
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
	
}
