package converters.enums;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.log4j.Logger;

import util.converters.EnumsUtil;
import converters.model.BaseConverter;

public class BaseEnumConverter<E extends Enum<?>> implements Converter{

	/* Logger */
    protected final Logger logger = Logger.getLogger(BaseConverter.class);
    
    /* Patterns */
   	private static final String PATTERN_UTIL = "Util";
   	private static final String PATH_UTIL = "util.converters.";
	
   	/* Enum */
	private Class<E> enums;
	
	/* Class Util */
	private Class<? extends EnumsUtil<E>> utilClazz;
	
	/* Util*/
	private EnumsUtil<E> util;
	
	public BaseEnumConverter(){
		this.setUtil(newInstanceUtil(getUtilClazz()));
	}
	
	@SuppressWarnings("unchecked")
	private Class<? extends EnumsUtil<E>> getUtilClazz() {
		if(this.utilClazz == null){
			try{
				this.utilClazz = (Class<? extends EnumsUtil<E>>) Class.forName(PATH_UTIL + this.getEnum().getSimpleName() + PATTERN_UTIL);
			} catch (ClassNotFoundException cnfE){
				logger.error("Util not found for this Enum" + this.getEnum().getSimpleName() + ": ",cnfE);
			}
		}
		return this.utilClazz;
	}

	/**
	 * Método que descobre qual é a classe por reflection do generics
	 * @author matheuscastro
	 * @author mauro
	 * @return Enum 
	 */
	@SuppressWarnings("unchecked")
	private Class<E> getEnum() {
		if(this.enums == null){
			Type tipo = ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			this.enums = (Class<E>) tipo;
		}
		return this.enums;
	}
	
	public EnumsUtil<E> newInstanceUtil(Class<? extends EnumsUtil<E>> clazzUtil){
		try{
			return clazzUtil.newInstance();
		} catch (InstantiationException ie) {
			logger.error("Error in create a newInstanceUtil for the " + clazzUtil.getSimpleName() + ": ", ie);
		} catch (IllegalAccessException iae) {
			logger.error("Error in create a newInstanceUtil for the " + clazzUtil.getSimpleName() + ": ", iae);
		} catch (Exception e){
			logger.error("Some error is occurred: ", e);
		}
		return null;
	}


	public EnumsUtil<E> getUtil() {
		return util;
	}

	public void setUtil(EnumsUtil<E> util) {
		this.util = util;
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length()<=0)
			return null;
		
		for(E e : this.getUtil().getList()){
			if(e.toString().equals(value))
				return e;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) {
		
		if(object.getClass().equals(enums))
			return ((E)object).toString();
		
		return null;
	}
	
}
