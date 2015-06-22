package converters.model;

import javax.faces.convert.FacesConverter;

import model.Funcionario;

@FacesConverter(value="funcionario-converter", forClass=Funcionario.class)
public class FuncionarioConverter extends BaseConverter<Funcionario>{

	public FuncionarioConverter() {
		
		super();
	}

}
