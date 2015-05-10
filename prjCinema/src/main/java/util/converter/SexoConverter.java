package util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.enums.Sexo;
import util.SexoUtil;

@FacesConverter(value="sexo-converter", forClass=SexoConverter.class)
public class SexoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length() <=0)
			return null;
		
		for(Sexo s : SexoUtil.getSexos()){
			if(s.name().equals(value))
				return s;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		if(obj instanceof Sexo){
			return ((Sexo)obj).name();
		}
		
		return null;
	}

	
}
