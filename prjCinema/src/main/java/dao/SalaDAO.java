package dao;

import javax.persistence.EntityManager;

import model.Sala;

public class SalaDAO extends GenericDAO<Sala>{

	public SalaDAO() {
		super();
	}

	public SalaDAO(EntityManager manager) {
		super(manager);
	}

	
}
