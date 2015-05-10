package listener;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import util.JSFUtil;
import bean.LoginBean;

public class LoginCheck implements PhaseListener{

	private static final long serialVersionUID = -5477914621530878816L;
	private static final String RAIZ_PATH = "/";
	private static final String ERROR_FOLDER = RAIZ_PATH + "error/";
	private static final String PAGE_ERROR_EXPIRED = ERROR_FOLDER + "expired.action";
	
	private static final String BEAN = "loginBean";

	@Override
	public void afterPhase(PhaseEvent event) {
		boolean usuarioAutenticado = false;
		LoginBean loginBean = (LoginBean) JSFUtil.getVariavelApplication(BEAN);

		if (loginBean != null)
			usuarioAutenticado = loginBean.isAutenticado();

		// ------------------------------------
		FacesContext contexto = event.getFacesContext();

		// Check to see if they are on the login page.
		boolean paginaLogin = contexto.getViewRoot().getViewId().lastIndexOf("login") > -1 ? true : false;
		if (!paginaLogin)
			paginaLogin = contexto.getViewRoot().getViewId().lastIndexOf("_expirado") > -1 ? true : false;

		if (!paginaLogin && !usuarioAutenticado)
		{
			NavigationHandler nh = contexto.getApplication().getNavigationHandler();
			nh.handleNavigation(contexto, null, PAGE_ERROR_EXPIRED);
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
