package annotations;

import java.lang.reflect.InvocationTargetException;

import model.BaseModel;

public interface Injection {

	public abstract <M extends BaseModel> void doInjection(Object target) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
}
