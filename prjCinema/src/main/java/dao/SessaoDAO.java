package dao;

import javax.persistence.EntityManager;

import model.Sessao;

public class SessaoDAO extends GenericDAO<Sessao>{

	public SessaoDAO() {
		super();
	}

	public SessaoDAO(EntityManager manager) {
		super(manager);
	}

	
}
