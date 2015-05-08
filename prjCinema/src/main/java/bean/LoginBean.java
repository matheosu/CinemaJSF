package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Funcionario;
import util.JSFUtil;
import util.SecurityUtil;
import dao.FuncionarioDAO;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {

	private static final String RAIZ_PATH = "/";
	private static final String RESTRICT_PATH = RAIZ_PATH + "restrito/";
	private static final String ACTION_LOGIN = RAIZ_PATH + "login.action";
	private static final String ACTION_MENU = RESTRICT_PATH + "principal.action";
	private static final String REDIRECT = "?faces-redirect=true";
	
	private Funcionario funcionario;
	private boolean autenticado;
	
	private Long matricula;
	private String senha;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String autenticar(){
		
		FuncionarioDAO daoF = new FuncionarioDAO();
		Funcionario func = daoF.findById(this.getMatricula());
		
		if(func == null){
			JSFUtil.retornarMensagemErro("Funcionário não existe", null, null);
			return ACTION_LOGIN;
		} else if(func.getSenha().equals(SecurityUtil.criptografarSenha(this.getSenha()))){
			this.setFuncionario(func);
			this.setAutenticado(true);
			this.matricula = null;
			this.senha = null;
			return ACTION_MENU + REDIRECT;
		}else{
			JSFUtil.retornarMensagemErro("Senha incorreta!",null,null);
			return ACTION_LOGIN;
		}
	}
	
	public String sair(){
		this.autenticado = false;
		this.funcionario = null;
		this.matricula = null;
		this.senha = null;

		JSFUtil.getHttpSession().invalidate();
		return ACTION_LOGIN + REDIRECT;
	}
	
	
}
