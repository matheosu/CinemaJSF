package dao;

import javax.persistence.EntityManager;

import model.Lugar;

public class LugarDAO extends GenericDAO<Lugar>{

	public LugarDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LugarDAO(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	
}
