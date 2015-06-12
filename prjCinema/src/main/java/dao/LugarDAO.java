package dao;

import javax.persistence.EntityManager;

import model.Lugar;

public class LugarDAO extends GenericDAO<Lugar>{

	public LugarDAO() {
		super();
	}

	public LugarDAO(EntityManager manager) {
		super(manager);
	}

	
}
