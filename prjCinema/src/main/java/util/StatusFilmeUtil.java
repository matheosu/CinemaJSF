package util;

import java.util.ArrayList;
import java.util.List;

import model.enums.StatusFilme;

public class StatusFilmeUtil {

	private static List<StatusFilme> statusFilme = new ArrayList<StatusFilme>();
	
	private StatusFilmeUtil(){}
	
	public static List<StatusFilme> getStatusFilme() {
		if(statusFilme.isEmpty()){
			statusFilme.add(StatusFilme.EXIBICAO);
			statusFilme.add(StatusFilme.LANCAMENTO);
			statusFilme.add(StatusFilme.PRE_LANCAMENTO);
			statusFilme.add(StatusFilme.INATIVO);
		}
		return statusFilme;
	}
	
}
