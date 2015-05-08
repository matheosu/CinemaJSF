package util.converter;

import javax.faces.convert.FacesConverter;

import model.Pessoa;

@FacesConverter(value="pessoa-converter", forClass=PessoaConverter.class)
public class PessoaConverter extends BaseConverter<Pessoa>{

	public PessoaConverter() {
		super();
	}

}
