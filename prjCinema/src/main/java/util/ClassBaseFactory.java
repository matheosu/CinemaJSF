package util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import model.BaseModel;

import org.apache.log4j.Logger;

import util.converters.EnumUtil;
import dao.IDAO;
import exception.ClassFactoryException;

public class ClassBaseFactory {

	private static final Logger log = Logger.getLogger(ClassBaseFactory.class);
	
	public static final String DEFAULT_PATTERN_DAO = "DAO";
	public static final String DEFAULT_PATH_DAO = "dao.";
	public static final String DEFAULT_PATTERN_UTIL = "Util";
	public static final String DEFAULT_PATH_UTIL = "util.converters.";

	// TODO Cache de Class dos DAOS já carregados
	//private static Map<Class<? extends BaseModel>, Class<? extends IDAO<? extends BaseModel>>> cacheDAOS = new HashMap<Class<? extends BaseModel>, Class<? extends IDAO<? extends BaseModel>>>();
	
	// TODO Cache de Class dos EnumsUtil já carregados
	//private static Map<Class<? extends Enum<?>>, Class<EnumsUtil<? extends Enum<?>>>> cacheEnumsUtil = new HashMap<Class<? extends Enum<?>>, Class<EnumsUtil<? extends Enum<?>>>>();

	public ClassBaseFactory(){}
	
	
	//###################### ENUM UTIL ###############################
	
	/**
	 * Procura o utilitario do Enum que está no generics na classe atual
	 * @param clazz
	 * @return EnumsUtil do Enum
	 */
	public static <E extends Enum<?>> EnumUtil<E> getEnumUtil(Class<E> clazzEnum){
		return getEnumUtil(clazzEnum,DEFAULT_PATH_UTIL,DEFAULT_PATTERN_UTIL);
	}
	
	/**
	 * Procura o utilitário do Enum passando o class do Enum e o
	 * caminho e padrão de utiliário existente
	 * 
	 * @param enumerator a classe do enum;
	 * @param path caminho de pacotes em que pode existir o utilitário do enum;
	 * @param pattern padrão associado ao utilitário
	 * @return
	 */
	public static <E extends Enum<?>> EnumUtil<E> getEnumUtil(
			Class<E> enumerator, String path, String pattern) {
		Class<EnumUtil<E>> utilClass = getUtilClass(enumerator, path, pattern);
		return newInstanceUtil(utilClass);
	}

	
	/**
	 * Cria uma nova instâcia do Utilitário passando o class da classe utilitiária
	 * <E> de Enum e <U> de classe EnumUtilitaria;
	 * @param classEnum
	 * @return retorna uma nova instância do utilitário
	 */
	private static final <E extends Enum<?>, U extends EnumUtil<E>> EnumUtil<E> newInstanceUtil(
			Class<U> utilClass) {
		try {
			return utilClass.newInstance();
		} catch (InstantiationException ie) {
			log.error("Error in create a newInstanceUtil for the "
					+ utilClass.getSimpleName() + ": " + ie.getMessage());
		} catch (IllegalAccessException iae) {
			log.error("Error in create a newInstanceUtil for the "
					+ utilClass.getSimpleName() + ": " + iae.getMessage());
		} catch (Exception e) {
			log.error("Some error is occurred: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Descbore qual é o classe do utilitário passando apenas o class do Enum
	 * <E> de Enum e <U> de classe EnumUtilitaria
	 * @param classEnum classe do enum
	 * @param path caminho onde se encontra o utilitário
	 * @param pattern padrão associado
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static final <E extends Enum<?>, U extends EnumUtil<E>> Class<U> getUtilClass(
			Class<E> classEnum, String path, String pattern) {
		try {
			if (validClass(classEnum) && validPathUtil(path)
					&& validPatternUtil(pattern))
				return (Class<U>) Class.forName(path
						+ classEnum.getSimpleName() + pattern);

		} catch (ClassFactoryException cfe) {
			log.error("Error in getUtilClass: " + cfe.getMessage());
		} catch (ClassNotFoundException cnfE) {
			log.error("Util not found for this Enum"
					+ classEnum.getSimpleName() + ": " + cnfE.getMessage());
		}
		return null;
	}

	//################################ DAO & Model ##########################################
	
	/**
	 * Descbore o Class<M> do <M> model passado por parametro
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <M extends BaseModel> Class<M> getModelClass(
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
			log.error("Error in getModelClass: " + cfe.getMessage());
		}
		return null;
	}

	public static <M extends BaseModel, D extends IDAO<M>> IDAO<M> getDAO(
			Class<M> model) {
		return getDAO(model, DEFAULT_PATH_DAO, DEFAULT_PATTERN_DAO);
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
			log.error("Erro in getDAO " + cfe.getMessage());
		} catch (InstantiationException ie) {
			log.error("Error in create a newInstanceDAO for the "
					+ daoClass.getSimpleName() + ": " + ie);
		} catch (IllegalAccessException iae) {
			log.error("Error in create a newInstanceDAO for the "
					+ daoClass.getSimpleName() + ": " + iae);
		} catch (Exception e) {
			log.error("Some error is occurred: " + e);
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
			log.error("Error in getDAOClass " + cfe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			log.error("DAO not found for this class" + model.getSimpleName()
					+ ": " + cnfe);
		}

		return null;
	}

	//########################## VALIDATORS #####################################/

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
		return validPattern(DEFAULT_PATTERN_UTIL, pattern);
	}

	private static final boolean validPathUtil(String path)
			throws ClassFactoryException {
		return validPatth(DEFAULT_PATH_UTIL, path);
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
		return validPatth(DEFAULT_PATH_DAO, path);
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
		return validPattern(DEFAULT_PATTERN_DAO, pattern);
	}

	private static final boolean validPattern(String defaultPattern,
			String pattern) throws ClassFactoryException {
		if (defaultPattern == null || defaultPattern.isEmpty())
			throw new ClassFactoryException("DefaultPattern is null or empty!");

		if (!pattern.toUpperCase().contains(pattern.toUpperCase()))
			throw new ClassFactoryException("Pattern is invalid!");

		return true;
	}

	private static final boolean validPatth(String defaultPath, String path)
			throws ClassFactoryException {
		if (defaultPath == null || defaultPath.isEmpty())
			throw new ClassFactoryException("Path is null or empty!");

		if(!path.contains("."))
			path +=".";
		
		if (!defaultPath.toUpperCase().contains(path.toUpperCase()))
			throw new ClassFactoryException("Path is invalid!");

		return true;
	}

}
