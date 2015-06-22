package bean.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Sessao;
import util.PathUtil;

@ManagedBean(name="sessaoBean")
@RequestScoped
public class SessaoBean extends BaseBean<Sessao>{

//	private static final Logger logger = Logger.getLogger(SessaoBean.class);
	
	public SessaoBean() {
		super();
	}

	@Override
	protected Sessao newInstance() {
		return new Sessao();
	}

	public static String show() {
		return PathUtil.getActionList(Sessao.class, true);
	}

}
