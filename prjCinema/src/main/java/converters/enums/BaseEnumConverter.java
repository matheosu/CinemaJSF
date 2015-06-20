package converters.enums;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.log4j.Logger;

import util.ClassBaseFactory;
import util.converters.EnumsUtil;
import converters.model.BaseConverter;

public class BaseEnumConverter<E extends Enum<?>> implements Converter{

	/* Logger */
    protected final Logger logger = Logger.getLogger(BaseConverter.class);
	
    /* Class Reflection */
    private Class<E> classEnum;
    
	/* Util*/
	private EnumsUtil<E> util;
	
	public BaseEnumConverter(){
		this.setUtil(ClassBaseFactory.getEnumUtil(getGeneric()));
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
		
		if(object.getClass().equals(getGeneric()))
			return ((E)object).toString();
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Class<E> getGeneric(){
		if(classEnum == null){
			Type type = ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
			classEnum = (Class<E>) type;
		}
		return classEnum;
	}
}
