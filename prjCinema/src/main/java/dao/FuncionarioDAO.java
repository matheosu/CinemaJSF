package dao;

import java.sql.BatchUpdateException;

import javax.persistence.EntityManager;

import model.Funcionario;

import org.apache.log4j.Logger;

public class FuncionarioDAO extends GenericDAO<Funcionario>{

	private static final Logger logger = Logger.getLogger(FuncionarioDAO.class);
	
	public FuncionarioDAO() {
		super();
	}

	public FuncionarioDAO(EntityManager manager) {
		super(manager);
	}

	@Override
	public Funcionario save(Funcionario funcionario) {
		
		Funcionario f = null;
		try{
			f = super.save(funcionario);
		} catch (Exception bue) {
			if (bue instanceof BatchUpdateException)
				logger.error(((BatchUpdateException)bue).getNextException());
			
			logger.error("Erro ao salvar funcionario: " + bue.getMessage());
		}
		
		return f;
	}

	
	
	
}
