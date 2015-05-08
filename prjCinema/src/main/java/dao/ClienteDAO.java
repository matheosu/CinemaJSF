package dao;

import javax.persistence.EntityManager;

import model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente>{

	public ClienteDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteDAO(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	
}
