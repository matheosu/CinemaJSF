package dao;

import javax.persistence.EntityManager;

import model.Genero;

public class GeneroDAO extends GenericDAO<Genero>{

	public GeneroDAO() {
		super();
	}

	public GeneroDAO(EntityManager manager) {
		super(manager);
	}

	
}
