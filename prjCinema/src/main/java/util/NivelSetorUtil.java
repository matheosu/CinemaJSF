package util;

import java.util.ArrayList;
import java.util.List;

import model.enums.NivelSetor;

public class NivelSetorUtil {

	private static List<NivelSetor> niveisSetor = new ArrayList<NivelSetor>();
	
	private NivelSetorUtil(){}
	
	public static List<NivelSetor> getNiveisSetor() {
		if(niveisSetor.isEmpty()){
			niveisSetor.add(NivelSetor.CONTROLE);
			niveisSetor.add(NivelSetor.OPERACAO);
			niveisSetor.add(NivelSetor.ADMINISTRACAO);
		}
		return niveisSetor;
	}
	
}
