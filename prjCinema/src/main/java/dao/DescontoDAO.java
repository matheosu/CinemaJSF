package dao;

import javax.persistence.EntityManager;

import model.Desconto;

public class DescontoDAO extends GenericDAO<Desconto>{

	public DescontoDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DescontoDAO(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	
}
