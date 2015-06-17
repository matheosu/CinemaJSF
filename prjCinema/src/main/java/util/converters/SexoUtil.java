package util.converters;

import java.util.ArrayList;
import java.util.List;

import model.enums.Sexo;

public class SexoUtil implements EnumsUtil<Sexo>{

	private static List<Sexo> sexos = new ArrayList<Sexo>();
	
	public SexoUtil(){}
	
	public static List<Sexo> getSexos(){
		
		if(sexos.isEmpty()){
			sexos.add(Sexo.FEMININO);
			sexos.add(Sexo.MASCULINO);
		}
		
		return sexos;
		
	}

	@Override
	public List<Sexo> getList() {
		return SexoUtil.getSexos();
	}
	
	
}
