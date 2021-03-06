package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Pessoa;

import org.apache.log4j.Logger;

public class PessoaDAO extends GenericDAO<Pessoa>{
	
	private static final Logger logger = Logger.getLogger(Pessoa.class);

	public PessoaDAO() {
		super();
	}

	public PessoaDAO(EntityManager manager) {
		super(manager);
	}

	public Pessoa findByCPF(String cpf){
		Pessoa pessoa;
		
		Query consulta = this.getEntityManager().createQuery("from Pessoa p where p.cpf =:cpf");
		consulta.setParameter("cpf", cpf);
		
		try{
			pessoa = (Pessoa) consulta.getSingleResult();
		}catch (NoResultException nrE){
			pessoa = null;
			logger.error("Error findByCPF: " + nrE.getMessage());
		}
		
		return pessoa;
	}
	
	public Pessoa findByRG(String rg){
		Pessoa pessoa;
		
		Query consulta = this.getEntityManager().createQuery("from Pessoa p where p.rg =:rg");
		consulta.setParameter("rg", rg);
		
		try{
			pessoa = (Pessoa) consulta.getSingleResult();
		}catch (NoResultException nrE){
			pessoa = null;
			logger.error("Error findByRG: " + nrE.getMessage());
		}
		
		return pessoa;
	}
	
	@SuppressWarnings("unchecked")
	public List<Pessoa> findByNome(String nome){
		List<Pessoa> pessoas;
		
		Query consulta = this.getEntityManager().createQuery("from Pessoa p where p.nome =:nome");
		consulta.setParameter("nome", nome);
		
		try{
			pessoas = (List<Pessoa>) consulta.getResultList();
		}catch (NoResultException nrE){
			pessoas = null;
			logger.error("Error findByNome: " + nrE.getMessage());
		}
		
		return pessoas;
		
	}
}
