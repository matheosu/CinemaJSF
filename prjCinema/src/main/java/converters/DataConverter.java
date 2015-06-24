package converters;

import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import util.CalendarUtil;

@FacesConverter("data-converter")
public class DataConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length() <=0)
			return null;
		
		return CalendarUtil.parseDateToCalendar(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		if(obj instanceof Calendar){
			return CalendarUtil.formatCalendarToStringDate((Calendar)obj);
		}else if(obj instanceof Date){
			Date date = (Date)obj;
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			return CalendarUtil.formatCalendarToStringDate(c);
		}
		return null;
	}

}
