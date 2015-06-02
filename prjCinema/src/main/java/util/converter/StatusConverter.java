package util.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import model.enums.StatusFilme;

@FacesConverter(value="status-converter", forClass=StatusFilme.class)
public class StatusConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length() <=0)
			return null;

		if(value.equals(StatusFilme.EXIBICAO)){
			return StatusFilme.EXIBICAO;
		} else if(value.equals(StatusFilme.INATIVO)){
			return StatusFilme.INATIVO;
		} else if(value.equals(StatusFilme.LANCAMENTO)){
			return StatusFilme.LANCAMENTO;
		} else if(value.equals(StatusFilme.PRE_LANCAMENTO)){
			return StatusFilme.PRE_LANCAMENTO;
		}
		
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		if(obj instanceof StatusFilme){
			StatusFilme statusTempo = (StatusFilme) obj;
			
			switch(statusTempo){
			case EXIBICAO:
				return StatusFilme.EXIBICAO.toString();
				
			case INATIVO:
				return StatusFilme.INATIVO.toString();
				
			case LANCAMENTO:
				return StatusFilme.LANCAMENTO.toString();
				
			case PRE_LANCAMENTO:
				return StatusFilme.PRE_LANCAMENTO.toString();
				
			default:
				return StatusFilme.INATIVO.toString();
			}
		}
		return null;
	}

}
