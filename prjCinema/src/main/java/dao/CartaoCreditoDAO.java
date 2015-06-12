package dao;

import javax.persistence.EntityManager;

import model.CartaoCredito;

public class CartaoCreditoDAO extends GenericDAO<CartaoCredito>{

	public CartaoCreditoDAO() {
		super();
	}

	public CartaoCreditoDAO(EntityManager manager) {
		super(manager);
	}

	
}
