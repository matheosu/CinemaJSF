package util.converters;

import java.util.ArrayList;
import java.util.List;

import model.enums.Classificacao;

public class ClassificacaoUtil implements EnumsUtil<Classificacao>{

	private static List<Classificacao> classificacoes = new ArrayList<Classificacao>();
	
	public ClassificacaoUtil(){}
	
	public static List<Classificacao> getClassificacoes() {
		if(classificacoes.isEmpty()){
			classificacoes.add(Classificacao.LIVRE);
			classificacoes.add(Classificacao.DOZE_ANOS);
			classificacoes.add(Classificacao.QUATORZE_ANOS);
			classificacoes.add(Classificacao.DEZESSEIS_ANOS);
			classificacoes.add(Classificacao.DEZOITO_ANOS);
		}
		
		return classificacoes;
	}

	@Override
	public List<Classificacao> getList() {
		return ClassificacaoUtil.getClassificacoes();
	}
	
}
