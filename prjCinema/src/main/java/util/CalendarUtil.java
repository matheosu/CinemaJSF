package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe Util Abstrata para não ser instânciada;
 * Converte Calendar em String e String em Calendar;
 * @author matheuscastro
 */
public abstract class CalendarUtil {

	/** O padrão usado para conversão de data.
	/* 	dd/MM/yyyy	 */
	private static final String DATE_PATTERN = "dd/MM/yyyy";
	
	/** O padrão usado para conversão de tempo.
	/* 	hh:mm:ss	 */
	private static final String TIME_PATTERN = "hh:mm:ss";
	
	/** O formatador de data. 
	 *  @see #DATE_PATTERN */
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_PATTERN);
	/** O formatador de tempo.
	 *  @see #TIME_PATTERN */
	private static final SimpleDateFormat TIME_FORMATTER = new SimpleDateFormat(TIME_PATTERN);
	
	
	/**
	 * Converte Calendar para uma String no formato {@link #DATE_FORMATTER}
	 * @param c calendar a ser convertido;
	 * @return Retorna String no formato {@link #DATE_FORMATTER}
	 */
	public static String formatCalendarToDate(Calendar c) {
		if (c == null)
			return null;

		return DATE_FORMATTER.format(c.getTime());
	}
	
	/**
	 * Converte Calendar para uma String no formato {@link #TIME_FORMATTER};
	 * @param c calendar a ser convertido;
	 * @return Retorna String no formato {@link #TIME_FORMATTER}
	 */
	public static String formatCalendarToTime(Calendar c){
		if (c == null)
			return null;

		return TIME_FORMATTER.format(c.getTime());
	}

	/**
	 * Converte uma string que esteja no formato {@link #DATE_FORMATTER}
	 * @param s string a ser convertida;
	 * @return Retorna um Calendar com a data informada no Parametro
	 */
	public static Calendar parseDateToCalendar(String s) {
		if (s == null)
			return null;

		if (!s.contains("/"))
			return null;

		String[] values = s.split("/");
		if (values.length > 2) {
			Integer dd = Integer.valueOf(values[0]);
			Integer MM = Integer.valueOf(values[1]);
			Integer yyyy = Integer.valueOf(values[2]);

			Calendar c = new GregorianCalendar();

			c.set(Calendar.DATE, dd);
			c.set(Calendar.MONTH, MM-1);
			c.set(Calendar.YEAR, yyyy);
			return c;
		}
		return null;
	}
	
	public static Calendar parseTimeToCalendar(String s){
		if (s == null)
			return null;

		if (!s.contains("/"))
			return null;

		String[] values = s.split("/");
		if (values.length > 2) {
			Integer hh = Integer.valueOf(values[0]);
			Integer mm = Integer.valueOf(values[1]);
			Integer ss = Integer.valueOf(values[2]);

			Calendar c = new GregorianCalendar();
			
			c.set(Calendar.HOUR, hh);
			c.set(Calendar.MINUTE, mm);
			c.set(Calendar.SECOND, ss);
			return c;
		}
		return null;
	}
}
