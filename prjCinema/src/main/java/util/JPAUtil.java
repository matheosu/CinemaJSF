package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil
{
	/**
	 * Objeto do tipo Singleton para criar a EntityManagerFactory como inst�ncia
	 * �nica durante a inicialia��o.
	 */
	private static EntityManagerFactory fabrica;

	static
	{
		JPAUtil.fabrica = Persistence.createEntityManagerFactory("banco");
	}

	public static EntityManagerFactory getFactory()
	{
		return JPAUtil.fabrica;
	}

	/**
	 * Objeto que armazena um �nico valor para cada Thread individualmente. O
	 * objetivo � criar apenas 1 objeto EntityManager para cada Thread, de forma
	 * que os DAO's possam compartilhar o mesmo objeto.
	 */
	private static ThreadLocal<EntityManager> CACHE = new ThreadLocal<EntityManager>();

	public static void limparCacheEntityManager()
	{
		// tenta ler o EntityManager da Thread atual
		EntityManager em = CACHE.get();

		// fecha o EntityManager caso exista
		if (em != null)
			em.close();

		CACHE.remove();
	}

	public static EntityManager getEntityManager()
	{
		// tenta ler o EntityManager da Thread atual
		EntityManager retorno = CACHE.get();

		// se tem um objeto e este objeto estiver fechado, deve descart�-lo e
		// criar outro
		if ((retorno != null) && (!retorno.isOpen()))
			retorno = null;

		// caso ainda n�o tenha sido criado, cria um novo e guarda reutiliza��o
		if (retorno == null)
		{
			retorno = JPAUtil.fabrica.createEntityManager();
			// guarda o objeto para usar sempre o mesmo nesta Thread
			CACHE.set(retorno);
		}

		return retorno;
	}

}
