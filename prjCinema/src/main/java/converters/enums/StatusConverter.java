package converters.enums;

import javax.faces.convert.FacesConverter;

import model.enums.StatusFilme;

@FacesConverter(value="status-converter", forClass=StatusFilme.class)
public class StatusConverter extends BaseEnumConverter<StatusFilme>{

	public StatusConverter() {
		super();
	}
}
