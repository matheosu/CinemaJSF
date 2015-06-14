package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import util.PathUtil;

@ManagedBean(name="menuBean")
@SessionScoped
public class MenuBean {
	
//	private static final Logger logger = Logger.getLogger(MenuBean.class);

	public String show(){
		return PathUtil.ACTION_MENU + PathUtil.REDIRECT;
	}
	
	public String getShow(){
		return show();
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
	
	public String login(){
		return PathUtil.ACTION_LOGIN + PathUtil.REDIRECT;
	}
}
