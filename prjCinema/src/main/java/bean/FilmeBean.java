package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Filme;
import model.enums.Classificacao;
import model.enums.StatusFilme;
import util.ClassificacaoUtil;
import util.PathUtil;
import util.StatusFilmeUtil;

@ManagedBean(name="filmeBean")
@RequestScoped
public class FilmeBean extends BaseBean<Filme>{

//	private static final Logger logger = Logger.getLogger(FilmeBean.class);
	
	public FilmeBean() {
		super();
	}

	public List<Classificacao> getClassificacoes(){
		return ClassificacaoUtil.getClassificacoes();
	}

	@Override
	protected Filme newInstance() {
		return new Filme();
	}
	
	public List<StatusFilme> getStatus(){
		return StatusFilmeUtil.getStatusFilme();
	}

	public static String show() {
		return PathUtil.getActionList(Filme.class, true);
	}
	
}
