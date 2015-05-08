package dao;

import javax.persistence.EntityManager;

import model.Funcionario;

public class FuncionarioDAO extends GenericDAO<Funcionario>{

	public FuncionarioDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FuncionarioDAO(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	
}
