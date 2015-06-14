package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import util.StatusFilmeUtil;
import model.enums.StatusFilme;

@FacesConverter(value="status-converter", forClass=StatusFilme.class)
public class StatusConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length() <=0)
			return null;

		for(StatusFilme sf : StatusFilmeUtil.getStatusFilme()){
			if(sf.getValue().equals(value))
				return sf;
		}
		
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if(obj instanceof StatusFilme){
			return ((StatusFilme) obj).getValue();
		}
		
		return null;
	}

}
