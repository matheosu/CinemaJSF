package bean.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import model.Funcionario;
import model.Setor;
import model.enums.Sexo;
import util.PathUtil;
import util.converters.SexoUtil;

@ManagedBean(name="funcionarioBean")
@RequestScoped
public class FuncionarioBean extends BaseBean<Funcionario>{

	@ManagedProperty("#{setorBean}")
	private SetorBean setorBean;
	
	public FuncionarioBean() {
		super();
	}

	public List<Sexo> getSexos() {
		return SexoUtil.getSexos();
	}
	
	public List<Setor> getSetores(){
		return setorBean.getInstances();
	}

	@Override
	protected Funcionario newInstance() {
		return new Funcionario();
	}

	public static String show() {
		return PathUtil.getActionList(Funcionario.class, true);
	}

	public SetorBean getSetorBean() {
		return setorBean;
	}

	public void setSetorBean(SetorBean setorBean) {
		this.setorBean = setorBean;
	}
	
	
}
