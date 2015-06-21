package util.converters;

import java.util.List;

/**
 * 
 * @author matheuscastro
 *
 * @param <E> Enumerado
 */
public interface EnumUtil<E extends Enum<?>> {

	public abstract List<E> getList();
}
