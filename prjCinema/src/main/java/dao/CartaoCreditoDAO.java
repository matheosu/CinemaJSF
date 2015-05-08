package dao;

import javax.persistence.EntityManager;

import model.CartaoCredito;

public class CartaoCreditoDAO extends GenericDAO<CartaoCredito>{

	public CartaoCreditoDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartaoCreditoDAO(EntityManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	
}
