package dao;

import javax.persistence.EntityManager;

import model.Ingresso;

public class IngressoDAO extends GenericDAO<Ingresso>{

	public IngressoDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IngressoDAO(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	
}
