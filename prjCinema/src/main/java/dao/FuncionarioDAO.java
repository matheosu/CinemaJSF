package dao;

import javax.persistence.EntityManager;

import model.Funcionario;

public class FuncionarioDAO extends GenericDAO<Funcionario>{

	public FuncionarioDAO() {
		super();
	}

	public FuncionarioDAO(EntityManager manager) {
		super(manager);
	}

	@Override
	public Funcionario save(Funcionario funcionario) {
		
		PessoaDAO dao = new PessoaDAO();
		funcionario.setPessoa(dao.save(funcionario.getPessoa()));
		
		
		return super.save(funcionario);
	}

	
	
	
}
