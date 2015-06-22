package bean.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Sala;
import util.PathUtil;

@ManagedBean(name="salaBean")
@RequestScoped
public class SalaBean extends BaseBean<Sala>{

//	private static final Logger logger = Logger.getLogger(SalaBean.class);
	
	public SalaBean() {
		super();
	}

	@Override
	protected Sala newInstance() {
		return new Sala();
	}

	
	/** Show Static URL 
	 * Retorna o endereço completo do XHTML de List do Bean junto com o REDIRECT!!!
	 * 
	 * @return o endereço completo do XHTML;
	 * @author matheuscastro
	 * */
	public static String show(){
		return PathUtil.getActionList(Sala.class, true);
	}
	
}
