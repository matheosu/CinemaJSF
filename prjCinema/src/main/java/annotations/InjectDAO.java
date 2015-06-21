package annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import model.BaseModel;
import util.ClassBaseFactory;
import dao.IDAO;

public class InjectDAO extends GenericInject {

	private static final Logger logger = Logger.getLogger(InjectDAO.class);
	
	
	private InjectDAO(){}
	
	@SuppressWarnings("unchecked")
	private static <M extends BaseModel> Map<Field,IDAO<M>> getInjectionFields(Field[] declaredFields) {
		Map<Field, IDAO<M>> fields = null;
		
		for (Field f : declaredFields){
			DAO daoAnnotation = f.getAnnotation(DAO.class);
			if(daoAnnotation != null){
				if(fields==null){
					fields = new HashMap<Field, IDAO<M>>();
				}
				
				Class<M> model = (Class<M>) daoAnnotation.value();
				if(model!=null){
					IDAO<M> dao = ClassBaseFactory.getDAO(model);
					if(dao!=null){
						fields.put(f, dao);
					}
				}
			}
		}
		
		return fields;
	}
	
	public static <M extends BaseModel> void doInjection(Object target) {
		Class<?> classe = target.getClass();
		Field[] declaredFields = classe.getDeclaredFields();

		Map<Field, IDAO<M>> map = getInjectionFields(declaredFields);
		if(map!=null){
			Set<Field> keys = map.keySet();
			for(Field f : keys){
				IDAO<M> dao = map.get(f);
				String fieldName = f.getName();
				fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
				
				Method setDAO = null;
				
				try {
					setDAO = classe.getMethod(PATTERN_SET_METHOD+fieldName, IDAO.class);
				} catch (NoSuchMethodException nsme) {
					logger.error(MSG_EXCEPTION + "Método não encontrado: " + nsme.getMessage());
				} catch (SecurityException se) {
					logger.error(MSG_EXCEPTION + "Acesso ao pacote da classe foi negado: " + se.getMessage());
				}
				
				if(setDAO!=null){
					try {
						setDAO.invoke(target,dao);
					} catch (IllegalAccessException iae) {
						logger.error(MSG_EXCEPTION + "O método subjacente é inacessível: " + iae.getMessage());
					} catch (IllegalArgumentException iae) {
						logger.error(MSG_EXCEPTION + "Erro ao passar valor para o parâmetro do método set: " + iae.getMessage());
					} catch (InvocationTargetException ite) {
						logger.error(MSG_EXCEPTION + "Método subjacente lançou uma exceção: " + ite.getMessage());
					}
				}
			}
		}
	}

	
}
