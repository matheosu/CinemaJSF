package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import model.Funcionario;
import model.Setor;
import model.enums.NivelSetor;

import org.apache.log4j.Logger;

import util.JSFUtil;
import util.PathUtil;
import util.SecurityUtil;
import annotations.DAO;
import annotations.InjectDAO;
import dao.IDAO;
import exception.BeanException;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
	
	private static final Logger logger = Logger.getLogger(LoginBean.class);
	
	@DAO
	private IDAO<Funcionario> dao;
	
	@ManagedProperty("#{menuBean}")
	private MenuBean menuBean;
	
	private Funcionario funcionario;
	private boolean autenticado;
	
	private Long matricula;
	private String senha;
	
	public LoginBean() {
		super();
		InjectDAO.doInjection(this);
	}

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
		Funcionario func = dao.findById(this.getMatricula());
		
		if(func == null){
			JSFUtil.retornarMensagemErro("Funcionário não existe", null, null);
			return PathUtil.actionLogin(false);
		} else if(func.getSenha().equals(SecurityUtil.criptografarSenha(this.getSenha()))){
			this.setFuncionario(func);
			this.setAutenticado(true);
			this.matricula = null;
			this.senha = null;
			return menuBean.show();
		}else{
			JSFUtil.retornarMensagemErro("Senha incorreta!",null,null);
			return PathUtil.actionLogin(false);
		}
	}

	public String login(){
		return PathUtil.actionLogin(true);
	}
	
	public String sair(){
		this.autenticado = false;
		this.funcionario = null;
		this.matricula = null;
		this.senha = null;

		JSFUtil.getHttpSession().invalidate();
		return PathUtil.actionLogin(true);
	}
	
	/**
	 * Retorna qual é o setor do funcionário
	 * @return
	 * @throws BeanException 
	 */
	public Setor getSetorFuncionario() throws BeanException {
		if(getFuncionario() == null)
			throw new BeanException("Não há Funcionário Logado !");
			
		if(getFuncionario().getSetor() == null)
			throw new BeanException("Funcionário Logado está sem setor!");
		
		return getFuncionario().getSetor();
	}
	
	/**
	 * Retorna o nível do setor do funcionário
	 * @return
	 */
	public NivelSetor getNivelSetorFuncionario(){
		try {
			if(getSetorFuncionario() != null)
				return getSetorFuncionario().getNivel();
		} catch (BeanException beanE) {
			logger.error("Erro ao descobrir o Nível do Setor do funcionário: " + beanE.getMessage());
		}
		return null;
	}
	
	public boolean isNivelOperacao(){
		if(getNivelSetorFuncionario() != null){
			if(getNivelSetorFuncionario() == NivelSetor.OPERACAO)
				return true;
		}
		return false;
	}
	
	public boolean isNivelControle(){
		if(getNivelSetorFuncionario() != null){
			if(getNivelSetorFuncionario() == NivelSetor.CONTROLE)
				return true;
		}
		return false;
	}
	
	public boolean isNivelAdministracao(){
		if(getNivelSetorFuncionario() != null){
			if(getNivelSetorFuncionario() == NivelSetor.ADMINISTRACAO)
				return true;
		}
		return false;
	}
	
	public boolean isGerente(){
		if(getFuncionario()!=null)
			return getFuncionario().isGerente();
		
		return false;
	}

	public IDAO<Funcionario> getDao() {
		return dao;
	}

	public void setDao(IDAO<Funcionario> dao) {
		this.dao = dao;
	}

	public MenuBean getMenuBean() {
		return menuBean;
	}

	public void setMenuBean(MenuBean menuBean) {
		this.menuBean = menuBean;
	}

}
