package converters;

import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.log4j.Logger;

import util.URLUtil;

@FacesConverter(value="url-converter", forClass=String.class)
public class URLConverter implements Converter{

	private static final Logger logger = Logger.getLogger(URLConverter.class);
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null || value.length() <=0)
			return null;
		try {
			return new URL(URLUtil.convertURLToEmbed(value));
		} catch (MalformedURLException mURLe) {
			logger.error("Erro ao converter URL [" + mURLe.getMessage() + "]: ", mURLe);
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
