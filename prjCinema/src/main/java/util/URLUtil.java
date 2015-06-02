package util;

public class URLUtil {
	
	private static final String HTTPS = "https://";
	private static final String YOUTUBE = "www.youtube.com/";
	private static final String EMBED = "embed/";
	private static final String WATCH = "watch?v=";
	private static final String LINK_EMBED = HTTPS + YOUTUBE + EMBED;
	private static final String LINK_WATCH = HTTPS + YOUTUBE + WATCH;

	private URLUtil(){}
	
	public static String convertURLToEmbed(final String url){
		if(url.contains("=")){
			String[] separado = url.split("=");
			return LINK_EMBED + separado[1];
		}
		return url;
	}
	
	public static String convertEmbedToURL(final String embed){
		if(embed.contains(EMBED)){
			String [] separado = embed.split(EMBED);
			return LINK_WATCH + separado[1];
		}
		return embed;
	}
}
