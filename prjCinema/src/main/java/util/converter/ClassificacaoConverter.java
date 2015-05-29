package util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.enums.Classificacao;
import bean.FilmeBean;

@FacesConverter(value="classificacao-converter" , forClass=Classificacao.class)
public class ClassificacaoConverter implements Converter{
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length() <=0)
			return null;
		
		for(Classificacao c : FilmeBean.getClassificacoes()){
			if(c.name().equals(value))
				return c;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {

		if(obj instanceof Classificacao)
			return ((Classificacao)obj).name();
		
		return null;
	}

}
