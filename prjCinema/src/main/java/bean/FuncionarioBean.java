package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.Funcionario;
import model.Setor;
import model.enums.Sexo;
import util.PathUtil;
import util.converters.SexoUtil;
import dao.SetorDAO;

@ManagedBean(name="funcionarioBean")
@RequestScoped
public class FuncionarioBean extends BaseBean<Funcionario>{

//	private static final Logger logger = Logger.getLogger(FuncionarioBean.class);
	
	public FuncionarioBean() {
		super();
	}

	public List<Sexo> getSexos() {
		return SexoUtil.getSexos();
	}
	
	public List<Setor> getSetores(){
		SetorDAO daoS = new SetorDAO();
		return daoS.getAll();
	}

	@Override
	protected Funcionario newInstance() {
		return new Funcionario();
	}

	@Override
	public String save() {
		return super.save();
	}
	
	public static String show() {
		return PathUtil.getActionList(Funcionario.class, true);
	}
	
}
