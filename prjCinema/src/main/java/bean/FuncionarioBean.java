package bean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import model.Funcionario;
import model.Setor;
import model.enums.Sexo;
import util.SexoUtil;
import dao.SetorDAO;

@ManagedBean(name="funcionarioBean")
public class FuncionarioBean extends BaseBean<Funcionario>{

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
	
}
