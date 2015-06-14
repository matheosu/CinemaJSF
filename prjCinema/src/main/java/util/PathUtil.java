package util;

import model.BaseModel;
import model.Sessao;
import model.Setor;

public abstract class PathUtil {

	/* Redirect */
	public static final String REDIRECT = "?faces-redirect=true";
	
	/* Patterns */
	private static final String PATTERN_ACTION_LIST = "s.action";
	private static final String PATTERN_ACTION_EDIT = "_edit.action";
	
	/* Paths */
	public static final String RAIZ_PATH = "/";
	public static final String RESTRICT_PATH = RAIZ_PATH + "restrito/";
	public static final String ACTION_MENU = RESTRICT_PATH + "principal.action";
	public static final String ACTION_LOGIN = RAIZ_PATH + "login.action";
	public static final String ERROR_FOLDER = RAIZ_PATH + "error/";
	public static final String PAGE_ERROR_EXPIRED = ERROR_FOLDER + "expired.action";	
	
	
	public static final String CONTROLE_PATH = RESTRICT_PATH + "controle/";
	public static final String OPERACAO_PATH = RESTRICT_PATH + "operacao/";
	public static final String ADMINISRACAO_PATH = RESTRICT_PATH + "administracao/";
	
	/* Class Controller */
	private static final String[] CLASSES_CONTROLE = {"filme","genero"};
	private static final String[] CLASSES_OPERACAO = {"sala","sessao"};
	private static final String[] CLASSES_ADMINISTRACAO = {"funcionario","setor"};
	
	/**
	 * Descobre qual é o endereço do arquivo de listagem da classe passada por parametro.
	 * @param clazz classe informada por parametro, classe tem que extender ou implementar BaseModel
	 * @param redirect informa se quer retornar com redirect ou não
	 * @return caminho completo mais o arquivo xhtml
	 * @author matheuscastro
	 */
	public static String getActionList(Class<? extends BaseModel> clazz, boolean redirect){
		String classLower = clazz.getSimpleName().toLowerCase();

		String urlPage = getAbsolutePath(classLower) + classLower + PATTERN_ACTION_LIST;
		
		if (clazz.equals(Setor.class))
			urlPage = urlPage.replace("setors", "setores");
		else if (clazz.equals(Sessao.class))
			urlPage = urlPage.replace("sessaos", "sessoes");
		
		if(redirect)
			return urlPage + REDIRECT;
		
		return urlPage;
	}
	
	/**
	 * Descobre qual é o endereço do arquivo de edição da classe passada por parametro.
	 * @param clazz classe informada por parametro, classe tem que extender ou implementar BaseModel
	 * @param redirect informa se quer retornar com redirect ou não
	 * @return caminho completo mais o arquivo xhtml
	 * @author matheuscastro
	 */
	public static String getActionEdit(Class<? extends BaseModel> clazz, boolean redirect){
		String classLower = clazz.getSimpleName().toLowerCase();;
		
		String urlPage = getAbsolutePath(classLower) + classLower + PATTERN_ACTION_EDIT;
		
		if(redirect)
			return urlPage + REDIRECT;
		
		return urlPage;
	}
	
	/**
	 * Método que determina o caminho absoluto de onde estará os actions de
	 * acordo com a classe informada por parametro
	 * 
	 * @param nameClass nome simples da classe em lowercase 
	 * @return String com o caminho absoluto
	 */
	private static String getAbsolutePath(String nameClass){
		
		for(String clazz : CLASSES_CONTROLE){
			if(clazz.equals(nameClass)){
				return CONTROLE_PATH + nameClass + "/";
			}
		}
		
		for(String clazz : CLASSES_OPERACAO){
			if(clazz.equals(nameClass)){
				return OPERACAO_PATH + nameClass + "/";
			}
		}
		
		for(String clazz : CLASSES_ADMINISTRACAO){
			if(clazz.equals(nameClass)){
				return ADMINISRACAO_PATH + nameClass + "/";
			}
		}
		
		return RESTRICT_PATH;
	}
}
