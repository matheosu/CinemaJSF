package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import util.NivelSetorUtil;
import model.enums.NivelSetor;

@FacesConverter(value = "nivelsetor-converter", forClass = NivelSetor.class)
public class NivelSetorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null || value.length() <= 0)
			return null;

		for(NivelSetor ns : NivelSetorUtil.getNiveisSetor()){
			if(ns.getDescricao().equals(value))
				return ns;
		}
		
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object obj) {
		if (obj instanceof NivelSetor) {
			return ((NivelSetor) obj).getDescricao();
		}

		return null;
	}

}
