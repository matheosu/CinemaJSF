package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import exception.ModelException;
import util.SecurityUtil;

@Entity
@Table(name="funcionarios")
public class Funcionario implements BaseModel{

	private static final long serialVersionUID = -2415490068253607338L;

	public static final int MAX_LENGTH_SENHA = 200;
	
	@Id
	@GeneratedValue(generator = "FUNCIONARIO_MATRICULA", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FUNCIONARIO_MATRICULA", sequenceName = "SEQ_FUNCIONARIO", allocationSize=1)
	private Long matricula;
	
	@Column(length = MAX_LENGTH_SENHA)
	private String senha;
	
	@ManyToOne
	private Setor setor;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="pessoa_id", nullable=false, updatable=true)
	private Pessoa pessoa;
	
	public Funcionario(){
		this.setPessoa(new Pessoa());
	}
	
	public Funcionario(String senha, Setor setor, Pessoa pessoa) throws ModelException{
		this.setSenha(senha);
		this.setSetor(setor);
		this.setPessoa(pessoa);
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
	public void setSenha(String senha) throws ModelException {
		if(validarSenha(senha))
			this.senha = SecurityUtil.criptografarSenha(senha);
	}

	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public static boolean validarSenha(String senha) throws ModelException {
		if(senha == null || senha.trim().length() > MAX_LENGTH_SENHA)
			throw new ModelException(Funcionario.class.getSimpleName() + ": Senha Nula ou Maior que Permitido");
		
		return true;
	}
	
	@Override
	public String toString() {
		return getPessoa().getNome() + " - " +  matricula;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public Long getId() {
		return this.getMatricula();
	}

	@Override
	public void setId(Long id) {
		this.setMatricula(id);		
	}

	public boolean isGerente() {
		if(getSetor() != null){
			if(getSetor().getGerente() != null){
				return getSetor().getGerente() == this;
			}
		}
		return false;
	}
}
