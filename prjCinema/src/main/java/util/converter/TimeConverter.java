package util.converter;

import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import util.CalendarUtil;

@FacesConverter(value="time-converter", forClass=Calendar.class)
public class TimeConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length()<=0)
			return null;
		
		return CalendarUtil.parseTimeToCalendar(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		if(obj instanceof Calendar)
			return CalendarUtil.formatCalendarToTime((Calendar)obj);
					
		return null;
	}

}
