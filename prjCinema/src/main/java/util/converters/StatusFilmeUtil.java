package util.converters;

import java.util.ArrayList;
import java.util.List;

import model.enums.StatusFilme;

public class StatusFilmeUtil implements EnumsUtil<StatusFilme>{

	private static List<StatusFilme> statusFilme = new ArrayList<StatusFilme>();
	
	public StatusFilmeUtil(){}
	
	public static List<StatusFilme> getStatusFilme() {
		if(statusFilme.isEmpty()){
			statusFilme.add(StatusFilme.EXIBICAO);
			statusFilme.add(StatusFilme.LANCAMENTO);
		}
		return statusFilme;
	}

	@Override
	public List<StatusFilme> getList() {
		return StatusFilmeUtil.getStatusFilme();
	}
	
}
