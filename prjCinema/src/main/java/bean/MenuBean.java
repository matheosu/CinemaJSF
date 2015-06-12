package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import util.PathUtil;

@ManagedBean(name="menuBean")
@SessionScoped
public class MenuBean {
	
	private static final Logger logger = Logger.getLogger(MenuBean.class);

	public String getShow(){
		return PathUtil.ACTION_MENU + PathUtil.REDIRECT;
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
