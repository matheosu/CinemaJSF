package converters.enums;

import javax.faces.convert.FacesConverter;

import model.enums.Sexo;

@FacesConverter(value="sexo-converter", forClass=Sexo.class)
public class SexoConverter extends BaseEnumConverter<Sexo>{

	public SexoConverter() {
		super();
	}

}
