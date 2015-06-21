package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import model.BaseModel;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DAO {
	/**
	 * Clase Model
	 * @return
	 */
	Class<? extends BaseModel> value();
}
