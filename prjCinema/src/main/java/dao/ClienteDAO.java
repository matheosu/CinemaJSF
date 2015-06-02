package dao;

import javax.persistence.EntityManager;

import model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente>{

	
	public ClienteDAO() {
		super();
	}

	public ClienteDAO(EntityManager manager) {
		super(manager);
	}

	@Override
	public Cliente save(Cliente cliente) {
		
		PessoaDAO daoP = new PessoaDAO();
		cliente.setPessoa(daoP.save(cliente.getPessoa()));
		
		return super.save(cliente);
	}

	
	
}
