package util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.logging.Logger;

import model.BaseModel;
import util.converters.EnumsUtil;
import util.exception.ClassFactoryException;
import dao.IDAO;

public class ClassBaseFactory {

	//TODO Alterar o Logger para o Log4j (APACHE)
	private static final Logger log = Logger.getLogger(ClassBaseFactory.class
			.getName());
	private static final String DEFAULT_PATTERN_DAO = "DAO";
	private static final String DEFAULT_PATH_DAO = "dao.";
	private static final String DEFAULT_PATTERN_UTIL = "Util";
	private static final String DEFAULT_PATH_UTIL = "util.convertes.";

	public static <E extends Enum<?>> EnumsUtil<E> getEnumUtil(
			Class<E> enumerator) {
		return getEnumUtil(enumerator, null, null);
	}

	public static <E extends Enum<?>> EnumsUtil<E> getEnumUtil(
			Class<E> enumerator, String path, String pattern) {
		Class<EnumsUtil<E>> utilClass = getUtilClass(enumerator, path, pattern);
		return newInstanceUtil(utilClass);
	}

	private static final <E extends Enum<?>> EnumsUtil<E> newInstanceUtil(
			Class<EnumsUtil<E>> utilClass) {
		try {
			return utilClass.newInstance();
		} catch (InstantiationException ie) {
			log.warning("Error in create a newInstanceUtil for the "
					+ utilClass.getSimpleName() + ": " + ie.getMessage());
		} catch (IllegalAccessException iae) {
			log.warning("Error in create a newInstanceUtil for the "
					+ utilClass.getSimpleName() + ": " + iae.getMessage());
		} catch (Exception e) {
			log.warning("Some error is occurred: " + e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private static final <E extends Enum<?>> Class<EnumsUtil<E>> getUtilClass(
			Class<E> enumerator, String path, String pattern) {
		try {
			if (validClass(enumerator) && validPathUtil(path)
					&& validPatternUtil(pattern))
				return (Class<EnumsUtil<E>>) Class.forName(path
						+ enumerator.getSimpleName() + pattern);

		} catch (ClassFactoryException cfe) {
			log.warning("Error in getUtilClass: " + cfe.getMessage());
		} catch (ClassNotFoundException cnfE) {
			log.warning("Util not found for this Enum"
					+ enumerator.getSimpleName() + ": " + cnfE.getMessage());
		}
		return null;
	}

	/**
	 * Descbore o Class<M> do <M> model passado por parametro
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static final <M extends BaseModel> Class<M> getModelClass(
			Class<?> model) {
		try {
			if (validClass(model)) {
				Type type = ((ParameterizedType) model.getClass()
						.getGenericSuperclass()).getActualTypeArguments()[0];
				if (validType(type)) {
					return (Class<M>) type;
				}
			}
		} catch (ClassFactoryException cfe) {
			log.warning("Error in getModelClass: " + cfe.getMessage());
		}
		return null;
	}

	public static <M extends BaseModel, D extends IDAO<M>> IDAO<M> getDAO(
			Class<M> model) {
		return getDAO(model, null, null);
	}

	/**
	 * Pega o DAO do model, através dos parametros
	 * 
	 * @param model
	 * @param path
	 * @param pattern
	 * @return
	 */
	public static <M extends BaseModel, D extends IDAO<M>> IDAO<M> getDAO(
			Class<M> model, String path, String pattern) {
		Class<D> daoClass = getDAOClass(model, path, pattern);
		return newInstanceDAO(daoClass);
	}

	/**
	 * Cria uma instância da Classe DAO passada
	 * 
	 * @param daoClass
	 * @return
	 */
	private static final <M extends BaseModel, D extends IDAO<M>> IDAO<M> newInstanceDAO(
			Class<D> daoClass) {
		try {
			if (validClass(daoClass))
				return daoClass.newInstance();
		} catch (ClassFactoryException cfe) {
			log.warning("Erro in getDAO " + cfe.getMessage());
		} catch (InstantiationException ie) {
			log.warning("Error in create a newInstanceDAO for the "
					+ daoClass.getSimpleName() + ": " + ie);
		} catch (IllegalAccessException iae) {
			log.warning("Error in create a newInstanceDAO for the "
					+ daoClass.getSimpleName() + ": " + iae);
		} catch (Exception e) {
			log.warning("Some error is occurred: " + e);
		}

		return null;
	}

	/**
	 * Descobre qual é a classe DAO do model passado
	 * 
	 * @param model
	 * @param path
	 * @param pattern
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static final <D extends IDAO<M>, M extends BaseModel> Class<D> getDAOClass(
			Class<M> model, String path, String pattern) {
		try {
			if (validClass(model) && validPatternDAO(pattern)
					&& validPathDAO(path))
				return (Class<D>) Class.forName(path + model.getSimpleName()
						+ pattern);
		} catch (ClassFactoryException cfe) {
			log.warning("Error in getDAOClass " + cfe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			log.warning("DAO not found for this class" + model.getSimpleName()
					+ ": " + cnfe);
		}

		return null;
	}

	/* VALIDATORS */

	private static final boolean validType(Type type)
			throws ClassFactoryException {
		if (type == null)
			throw new ClassFactoryException("Type is null");

		return true;
	}

	/**
	 * Valida se a classe Model que extends de BaseModel é nula
	 * 
	 * @param model
	 * @return
	 * @throws ClassFactoryException
	 */
	private static final boolean validClass(Class<?> clazz)
			throws ClassFactoryException {
		if (clazz == null)
			throw new ClassFactoryException("Class is null");

		return true;
	}

	private static final boolean validPatternUtil(String pattern)
			throws ClassFactoryException {
		if (validPattern(DEFAULT_PATTERN_UTIL, pattern))
			return true;

		return false;
	}

	private static final boolean validPathUtil(String path)
			throws ClassFactoryException {
		if (validPatth(DEFAULT_PATH_UTIL, path))
			return true;

		return false;
	}

	/**
	 * Valida se o caminho para procurar o dao é nulo e se está no padrão
	 * 
	 * @param path
	 * @return
	 * @throws ClassFactoryException
	 */
	private static final boolean validPathDAO(String path)
			throws ClassFactoryException {
		if (validPatth(DEFAULT_PATH_DAO, path))
			return true;

		return false;
	}

	/**
	 * Valida se o padrão está corerto ou não
	 * 
	 * @param pattern
	 * @return
	 * @throws ClassFactoryException
	 */
	private static final boolean validPatternDAO(String pattern)
			throws ClassFactoryException {
		if (validPattern(DEFAULT_PATTERN_DAO, pattern))
			return true;

		return false;
	}

	private static final boolean validPattern(String defaultPattern,
			String pattern) throws ClassFactoryException {
		if (defaultPattern == null || defaultPattern.isEmpty())
			throw new ClassFactoryException("DefaultPattern is null or empty!");

		if (pattern == null || pattern.isEmpty())
			pattern = defaultPattern;

		if (!pattern.toUpperCase().contains(pattern.toUpperCase()))
			throw new ClassFactoryException("Pattern is invalid!");

		return true;
	}

	private static final boolean validPatth(String defaultPath, String path)
			throws ClassFactoryException {
		if (defaultPath == null || defaultPath.isEmpty())
			throw new ClassFactoryException("Path is null or empty!");

		if (path == null || path.isEmpty())
			path = defaultPath;

		if (!defaultPath.toUpperCase().contains(path.toUpperCase())
				&& !path.contains("."))
			throw new ClassFactoryException("Path is invalid!");

		return true;
	}

}
