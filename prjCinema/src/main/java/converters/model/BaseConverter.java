package converters.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import model.BaseModel;

import org.apache.log4j.Logger;

import dao.IDAO;

public class BaseConverter<T extends BaseModel> implements Converter{

	/* Logger */
    protected final Logger logger = Logger.getLogger(BaseConverter.class);	
    /* Patterns */
   	private static final String PATTERN_DAO = "DAO";
   	private static final String PATH_DAO = "dao.";
    
    /* Classes */
	private Class<T> clazz;
	private Class<? extends IDAO<T>> daoClazz;
	
	/* DAO */
	protected IDAO<T> dao; 
	
	public BaseConverter(){
		this.dao = newInstanceDAO(getDAOClazz());
	}
	
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
	
	/**
	 * Método que descobre qual é a classe por reflection do generics
	 * @author matheuscastro
	 * @author mauro
	 * @return Classe 
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
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length() <= 0)
			return null;
		
		return dao.findById(converterValueToLong(value));
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		if(obj.getClass() == this.getClazz())
			return ((T) obj).getId().toString();
		
		return null;
	}

	/**
	 * Método que verifica se o valor passado é apenas número e converte para Long;
	 * Caso não seja apenas número converte para um Long de 0
	 * @param value
	 * @return Valor convertido em Long
	 * @author matheuscastro
	 */
	private Long converterValueToLong(String value){
		if(value == null || value.trim().length()<=0){
			logger.error("Value is null or empty");
			return new Long(0);
		}
		
		char[] chars = value.toCharArray();
		
		for(char c : chars){
			if(!Character.isDigit(c)){
				logger.warn("Value contains Letters then was convert to new Long(0)");
				return new Long(0);
			}
		}
		
		return Long.valueOf(value);
	}
	
}
