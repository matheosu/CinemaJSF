package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Genero;
import util.PathUtil;

@ManagedBean(name="generoBean")
@RequestScoped
public class GeneroBean extends BaseBean<Genero>{

//	private static final Logger logger = Logger.getLogger(GeneroBean.class);
	
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
