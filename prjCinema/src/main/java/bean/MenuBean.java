package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="menuBean")
@SessionScoped
public class MenuBean {

	private static final String RAIZ_PATH = "/";
	private static final String RESTRICT_PATH = RAIZ_PATH + "restrito/";
	private static final String ACTION_MENU = RESTRICT_PATH + "principal.action";
	private static final String REDIRECT = "?faces-redirect=true";
	
	public String getShow(){
		return ACTION_MENU + REDIRECT;
	}
	
	public String getShowGeneros(){
		return GeneroBean.show();
	}
	
	public String getShowFilmes(){
		return FilmeBean.show();
	}
	
	public String getShowSalas(){
		return SalaBean.show();
	}
	
	public String getShowSessoes(){
		return SessaoBean.show();
	}
	
	public String getShowFuncionarios(){
		return FuncionarioBean.show();
	}
	
	public String getShowSetores(){
		return SetorBean.show();
	}
}
