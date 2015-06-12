package dao;

import javax.persistence.EntityManager;

import model.Desconto;

public class DescontoDAO extends GenericDAO<Desconto>{

	public DescontoDAO() {
		super();
	}

	public DescontoDAO(EntityManager manager) {
		super(manager);
	}

	
}
