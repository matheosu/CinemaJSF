package converters;

import javax.faces.convert.FacesConverter;

import model.Genero;

@FacesConverter(value="genero-converter", forClass=Genero.class)
public class GeneroConverter extends BaseConverter<Genero>{

	public GeneroConverter() {
		super();
	}

}
