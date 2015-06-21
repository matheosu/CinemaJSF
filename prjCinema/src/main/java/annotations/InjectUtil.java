package annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import util.ClassBaseFactory;
import util.converters.EnumUtil;

public class InjectUtil extends GenericInject{

	private static final Logger logger = Logger.getLogger(InjectUtil.class);

	private InjectUtil() {}
	
	@SuppressWarnings("unchecked")
	private static <E extends Enum<?>> Map<Field,EnumUtil<E>> getInjectionFields(Field[] declaredFields) {
		Map<Field,EnumUtil<E>> fields = null;
		
		for (Field f : declaredFields){
			Util utilAnnotation = f.getAnnotation(Util.class);
			if(utilAnnotation != null){
				if(fields==null){
					fields = new HashMap<Field,EnumUtil<E>>();
				}
				
				Class<E> enumerator = (Class<E>) utilAnnotation.value();
				if(enumerator!=null){
					EnumUtil<E> util = ClassBaseFactory.getEnumUtil(enumerator);
					if(util!=null){
						fields.put(f, util);
					}
				}
			}
		}
		
		return fields;
	}
	
	public static <E extends Enum<?>> void doInjection(Object target) {
		Class<?> classe = target.getClass();
		Field[] declaredFields = classe.getDeclaredFields();

		Map<Field, EnumUtil<E>> map = getInjectionFields(declaredFields);
		if(map!=null){
			Set<Field> keys = map.keySet();
			for(Field f : keys){
				EnumUtil<E> util = map.get(f);
				String fieldName = f.getName();
				fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
				
				Method setUtil = null;
				
				try {
					setUtil = classe.getMethod(PATTERN_SET_METHOD+fieldName, EnumUtil.class);
				} catch (NoSuchMethodException nsme) {
					logger.error(MSG_EXCEPTION + "Método não encontrado: " + nsme.getMessage());
				} catch (SecurityException se) {
					logger.error(MSG_EXCEPTION + "Acesso ao pacote da classe foi negado: " + se.getMessage());
				}
				
				if(setUtil!=null){
					try {
						setUtil.invoke(target,util);
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
