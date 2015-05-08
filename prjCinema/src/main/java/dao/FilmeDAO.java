package dao;

import javax.persistence.EntityManager;

import model.Filme;

public class FilmeDAO extends GenericDAO<Filme>{

	public FilmeDAO() {
		super();
	}

	public FilmeDAO(EntityManager manager) {
		super(manager);
	}

	
}
