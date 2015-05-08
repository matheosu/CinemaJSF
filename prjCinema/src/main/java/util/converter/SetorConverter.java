package util.converter;

import javax.faces.convert.FacesConverter;

import model.Setor;

@FacesConverter(value="setor-converter", forClass=SetorConverter.class)
public class SetorConverter extends BaseConverter<Setor>{

	public SetorConverter() {
		super();
	}

}
