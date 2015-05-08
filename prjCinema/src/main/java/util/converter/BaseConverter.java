package util.converter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import model.BaseModel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import dao.IDAO;

public class BaseConverter<T extends BaseModel> implements Converter{

	/* Logger */
    protected final Logger logger = LogManager.getLogger(this.getPersistenceClass().getClass());
	
    /* Patterns */
   	private static final String PATTERN_DAO = "DAO";
   	private static final String PATH_DAO = "dao.";
    
    /* Classes */
	private Class<BaseModel> persistentClass;
	private Class<?> daoClass;
	
	/* DAO */
	private IDAO<BaseModel> dao; 
	
	@SuppressWarnings("unchecked")
	public BaseConverter(){
		this.getPersistenceClass();
		
		try {
			this.daoClass = Class.forName(PATH_DAO + this.persistentClass.getSimpleName() + PATTERN_DAO);
			this.dao = (IDAO<BaseModel>) daoClass.newInstance();
		} catch (ClassNotFoundException cnfE) {
			logger.error("DAO not found for this class" + this.persistentClass.getSimpleName() + ": " + cnfE.getMessage());
		} catch (InstantiationException iE) {
			logger.error("Error in create a newInstance for DAO: " + iE.getMessage());
		} catch (IllegalAccessException iaE) {
			logger.error("Error in create a newInstance for DAO: " + iaE.getMessage());
		}
	}
	
	/**
	 * Método que descobre qual é a classe por reflection do generics
	 * @author matheuscastro
	 * @author mauro
	 * @return Classe 
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
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length() <= 0)
			return null;
		
		return dao.findById(new Long(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		if(obj.getClass() == this.getPersistenceClass().getClass())
			return ((BaseModel) obj).getId().toString();
		
		return null;
	}

	
}
