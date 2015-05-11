package bean.scoped;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Flash implements Serializable{

	private static final long serialVersionUID = 8077578933742749258L;
	
	private static Map<String, Object> flashScoped;
	
	public Flash(){}


	public static Map<String, Object> getFlashScoped(){
		if(flashScoped == null){
			flashScoped = new HashMap<String, Object>();
		}
		
		return flashScoped;
	}
	
	public static void put(String key, Object o){
		getFlashScoped().put(key, o);
	}
	
	public static Object get(String key){
		return getFlashScoped().get(key);
	}
	
}
