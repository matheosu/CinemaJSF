package util;

import java.util.ArrayList;
import java.util.List;

import model.enums.Sexo;

public class SexoUtil {

	private static List<Sexo> sexos = new ArrayList<Sexo>();
	
	private SexoUtil(){}
	
	public static List<Sexo> getSexos(){
		
		if(sexos.isEmpty()){
			sexos.add(Sexo.FEMININO);
			sexos.add(Sexo.MASCULINO);
		}
		
		return sexos;
		
	}
	
	
}
