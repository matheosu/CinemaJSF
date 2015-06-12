package bean;

import model.Setor;

import org.apache.log4j.Logger;

import util.PathUtil;

public class SetorBean extends BaseBean<Setor>{

	private static final Logger logger = Logger.getLogger(SetorBean.class);
	
	public SetorBean() {
		super();
	}

	@Override
	protected Setor newInstance() {
		return new Setor();
	}

	public static String show() {
		return PathUtil.getActionList(Setor.class, true);
	}

}
