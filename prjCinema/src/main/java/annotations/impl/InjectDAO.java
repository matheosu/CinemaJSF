package annotations.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.BaseModel;
import util.ClassBaseFactory;
import annotations.DAO;
import annotations.Injection;
import bean.LoginBean;
import dao.IDAO;

public class InjectDAO implements Injection{


	@SuppressWarnings("unchecked")
	private static <M extends BaseModel> Map<Field,IDAO<M>> getInjectionFields(Field[] declaredFields) {
		Map<Field, IDAO<M>> fields = null;
		
		for (Field f : declaredFields){
			DAO daoAnnotation = f.getAnnotation(DAO.class);
			if(daoAnnotation != null){
				if(fields==null){
					fields = new HashMap<Field, IDAO<M>>();
				}
				
				Class<M> model = (Class<M>) daoAnnotation.model();
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
	
	public static void main(String... args){
		LoginBean loginBean = new LoginBean();
		InjectDAO inject = new InjectDAO();
		
		try {
			inject.doInjection(loginBean);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public <M extends BaseModel> void doInjection(Object target) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> classe = target.getClass();
		Field[] declaredFields = classe.getDeclaredFields();

		Map<Field, IDAO<M>> map = getInjectionFields(declaredFields);
		if(map!=null){
			Set<Field> keys = map.keySet();
			for(Field f : keys){
				IDAO<M> dao = map.get(f);
				String fieldName = f.getName();
				fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
				Method setDAO = classe.getMethod("set"+fieldName, IDAO.class);
				setDAO.setAccessible(true);
				Object o = setDAO.invoke(dao,"");
			}
		}
	}

	
}
