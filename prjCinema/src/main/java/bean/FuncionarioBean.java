package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Funcionario;
import model.Setor;
import model.enums.Sexo;
import dao.SetorDAO;

@ManagedBean(name="funcionarioBean")
@SessionScoped
public class FuncionarioBean extends BaseBean<Funcionario>{

	private static List<Sexo> sexos = new ArrayList<Sexo>();
	
	static{
		sexos.add(Sexo.FEMININO);
		sexos.add(Sexo.MASCULINO);
	}
	
	public FuncionarioBean() {
		super();
	}

	public static List<Sexo> getSexos() {
		return sexos;
	}
	
	
	public List<Setor> getSetores(){
		SetorDAO daoS = new SetorDAO();
		return daoS.getAll();
	}
	
}
