package bean;

import model.Sessao;

import org.apache.log4j.Logger;

import util.PathUtil;

public class SessaoBean extends BaseBean<Sessao>{

	private static final Logger logger = Logger.getLogger(SessaoBean.class);
	
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
