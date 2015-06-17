package converters.enums;

import javax.faces.convert.FacesConverter;

import model.enums.Classificacao;

@FacesConverter(value="classificacao-converter" , forClass=Classificacao.class)
public class ClassificacaoConverter extends BaseEnumConverter<Classificacao>{

	public ClassificacaoConverter() {
		super();
	}

}
