package converters.enums;

import javax.faces.convert.FacesConverter;

import model.enums.NivelSetor;

@FacesConverter(value = "nivelsetor-converter", forClass = NivelSetor.class)
public class NivelSetorConverter extends BaseEnumConverter<NivelSetor>{

	public NivelSetorConverter() {
		super();
	}
	
}
