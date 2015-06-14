package converters;

import javax.faces.convert.FacesConverter;

import model.Setor;

@FacesConverter(value="setor-converter", forClass=Setor.class)
public class SetorConverter extends BaseConverter<Setor>{

	public SetorConverter() {
		super();
	}


}
