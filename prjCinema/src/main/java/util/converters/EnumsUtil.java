package util.converters;

import java.util.List;

/**
 * 
 * @author matheuscastro
 *
 * @param <E> Enumerado
 */
public interface EnumsUtil<E extends Enum<?>> {

	public abstract List<E> getList();
}
