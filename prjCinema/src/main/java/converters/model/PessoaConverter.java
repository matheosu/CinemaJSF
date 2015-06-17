package converters.model;

import javax.faces.convert.FacesConverter;

import model.Pessoa;

@FacesConverter(value="pessoa-converter", forClass=Pessoa.class)
public class PessoaConverter extends BaseConverter<Pessoa>{

	public PessoaConverter() {
		super();
	}

}
