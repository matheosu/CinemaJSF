package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.BaseModel;

@FacesConverter("model-converter")
public class ModelConverter implements Converter{

	public ModelConverter(){}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length() <= 0)
			return null;
		
		return component.getAttributes().get(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		if(obj == null)
			return null;
		
		if(obj instanceof BaseModel){
			BaseModel bm = (BaseModel) obj;
			String key  = (bm.getId() == null ? String.valueOf(bm.hashCode()) : obj.getClass().getSimpleName() + bm.getId()); 
			component.getAttributes().put(key, bm);
			
			return key;
		}
		return null;
	}
}
