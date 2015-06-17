package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Setor;
import model.enums.NivelSetor;
import util.PathUtil;
import util.converters.NivelSetorUtil;

@ManagedBean(name="setorBean")
@RequestScoped
public class SetorBean extends BaseBean<Setor>{

//	private static final Logger logger = Logger.getLogger(SetorBean.class);
	
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
	
	
	public List<NivelSetor> getNiveis(){
		return NivelSetorUtil.getNiveisSetor();
	}
}
