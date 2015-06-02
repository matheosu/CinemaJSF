package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import model.Setor;

import org.apache.log4j.Logger;

public class SetorDAO extends GenericDAO<Setor>{
	
	private static final Logger logger = Logger.getLogger(Setor.class);

	public SetorDAO() {
		super();
	}

	public SetorDAO(EntityManager manager) {
		super(manager);
	}

	
	public Setor findByDesc(String desc){
		Setor setor;
		
		Query consulta = this.getEntityManager().createQuery("from Setor s where s.descricao = :desc");
		consulta.setParameter("desc", desc);
		
		try{
			setor = (Setor) consulta.getSingleResult();
		} catch(NoResultException nrE){
			setor = null;
			logger.error("Error findByDesc: " + nrE.getMessage());
		}
		
		return setor;
	}
	
	@SuppressWarnings("unchecked")
	public List<Setor> findByNivel(Integer nivel){
		List<Setor> setores;
		
		Query consulta = this.getEntityManager().createQuery("from Setor s where s.nivel = :nivel");
		consulta.setParameter("nivel", nivel);
		
		try{
			setores = (List<Setor>) consulta.getResultList();
		} catch(NoResultException nrE){
			setores = null;
			logger.error("Error findByNivel: " + nrE.getMessage());
		}
		
		return setores;
	}
	
}
