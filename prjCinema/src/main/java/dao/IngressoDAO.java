package dao;

import javax.persistence.EntityManager;

import model.Ingresso;

public class IngressoDAO extends GenericDAO<Ingresso>{

	public IngressoDAO() {
		super();
	}

	public IngressoDAO(EntityManager manager) {
		super(manager);
	}

	
}
