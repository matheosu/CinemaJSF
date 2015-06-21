package util.converters;

import java.util.ArrayList;
import java.util.List;

import model.enums.NivelSetor;

public class NivelSetorUtil implements EnumUtil<NivelSetor>{

	private static List<NivelSetor> niveisSetor = new ArrayList<NivelSetor>();
	
	public NivelSetorUtil(){}
	
	public static List<NivelSetor> getNiveisSetor() {
		if(niveisSetor.isEmpty()){
			niveisSetor.add(NivelSetor.CONTROLE);
			niveisSetor.add(NivelSetor.OPERACAO);
			niveisSetor.add(NivelSetor.ADMINISTRACAO);
		}
		return niveisSetor;
	}

	@Override
	public List<NivelSetor> getList() {
		return NivelSetorUtil.getNiveisSetor();
	}
	
}
