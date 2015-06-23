package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("enum-converter")
public class EnumConverter implements Converter{

	public EnumConverter(){
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length()<=0)
			return null;
		
		return component.getAttributes().get(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		if(obj == null)
			return null;
		
		String key = obj.getClass().getSimpleName() + obj.toString();
		component.getAttributes().put(key, obj);
		return key;
	}
	
}
