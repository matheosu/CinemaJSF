package converters.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import model.BaseModel;

import org.apache.log4j.Logger;

import util.ClassBaseFactory;
import dao.IDAO;

public class BaseConverter<M extends BaseModel> implements Converter{

	/* Logger */
    protected final Logger logger = Logger.getLogger(BaseConverter.class);	
    
    /* Class Reflection */
    private Class<M> classModel;
    
	/* DAO */
	protected IDAO<M> dao; 
	
	public BaseConverter(){
		this.dao = ClassBaseFactory.getDAO(getGeneric());
	}
	
	/**
	 * Método que descobre qual é a classe por reflection do generics
	 * @author matheuscastro
	 * @author mauro
	 * @return Classe 
	 */
	@SuppressWarnings("unchecked")
	private Class<M> getGeneric() {
		if(classModel==null){
			Type tipo = ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			classModel = (Class<M>) tipo;
		}
		return classModel;
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
		
		if(obj.getClass() == this.getGeneric())
			return ((M) obj).getId().toString();
		
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
