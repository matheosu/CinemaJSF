package bean;

import model.Genero;

import org.apache.log4j.Logger;

import util.PathUtil;

public class GeneroBean extends BaseBean<Genero>{

	private static final Logger logger = Logger.getLogger(GeneroBean.class);
	
	public GeneroBean() {
		super();
	}

	@Override
	protected Genero newInstance() {
		return new Genero();
	}

	public static String show() {
		return PathUtil.getActionList(Genero.class, true);
	}

}
