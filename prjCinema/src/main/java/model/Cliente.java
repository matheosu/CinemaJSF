package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import util.SecurityUtil;

@Entity
@Table(name = "clientes")
public class Cliente implements BaseModel{

	private static final long serialVersionUID = -3001268250772296055L;
	
	public static final int MAX_LENGTH_EMAIl = 200;
	public static final int MAX_LENGTH_SENHA = 200;
	
	@Id
	@GeneratedValue(generator = "CLIENTE_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "CLIENTE_ID", sequenceName = "SEQ_PESSOA", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false, unique = true, length = MAX_LENGTH_EMAIl)
	private String email;
	
	@Column(nullable = false, length = MAX_LENGTH_SENHA)
	private String senha;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente")
	private List<Ingresso> ingressos;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cliente")
	private List<CartaoCredito> cartoesCredito;

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Pessoa pessoa;
	
	public Cliente() {
		this.initCollections();
	}

	public Cliente(String email, String senha, Pessoa pessoa) {
		this.setEmail(email);
		this.setSenha(senha);
		this.setPessoa(pessoa);
		this.initCollections();
	}

	private void initCollections() {
		this.setIngressos(new ArrayList<Ingresso>());
		this.setCartoesCredito(new ArrayList<CartaoCredito>());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = SecurityUtil.criptografarSenha(senha);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public List<CartaoCredito> getCartoesCredito() {
		return cartoesCredito;
	}

	public void setCartoesCredito(List<CartaoCredito> cartoesCredito) {
		this.cartoesCredito = cartoesCredito;
	}

	@Override
	public String toString() {
		return getPessoa() + " - " + this.email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		
		return true;
	}

}
