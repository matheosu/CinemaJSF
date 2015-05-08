package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="menuBean")
@SessionScoped
public class MenuBean {

	private static final String RAIZ_PATH = "/";
	private static final String RESTRICT_PATH = RAIZ_PATH + "restrito/";
	private static final String ACTION_MENU = RESTRICT_PATH + "principal.action";
	
	public String show(){
		return ACTION_MENU;
	}
}
