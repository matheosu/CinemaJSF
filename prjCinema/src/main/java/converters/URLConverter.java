package converters;

import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import util.URLUtil;

@FacesConverter(value="url-converter", forClass=String.class)
public class URLConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length() <=0)
			return null;
		try {
			return new URL(URLUtil.convertURLToEmbed(value));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} 
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object obj) {
		
		if(obj instanceof URL){
			URL url = (URL) obj;

			return URLUtil.convertEmbedToURL(url.getPath());
		}
		return null;
	}

}
