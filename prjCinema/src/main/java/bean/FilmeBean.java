package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import model.Filme;
import model.enums.Classificacao;

@ManagedBean(name="filmeBean")
public class FilmeBean extends BaseBean<Filme>{

	private static List<Classificacao> classificacoes = new ArrayList<Classificacao>();
	
	public FilmeBean() {
		super();
	}

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
	
}
