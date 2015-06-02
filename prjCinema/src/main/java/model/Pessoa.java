package model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.enums.Sexo;

@Entity
@Table(name="pessoas")
public class Pessoa extends BaseModel{

	public static final int MAX_LENGTH_RG = 13;
	public static final int MAX_LENGTH_CPF = 14;
	public static final int MAX_LENGTH_NOME = 200;

	@Id
	@GeneratedValue(generator = "PESSOA_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "PESSOA_ID", sequenceName = "SEQ_PESSOA", allocationSize = 1)
	private Long id;

	@Column(nullable = true, unique = true, length = MAX_LENGTH_RG)
	private String rg;
	
	@Column(nullable = false, unique = true, length = MAX_LENGTH_CPF)
	private String cpf;
	
	@Column(length = MAX_LENGTH_NOME)
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(length=Sexo.MAX_LENGHT)
	private Sexo sexo;
	
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;

	@OneToOne(mappedBy="pessoa",cascade=CascadeType.ALL)
	private Cliente cliente;
	
	@OneToOne(mappedBy="pessoa",cascade=CascadeType.MERGE)
	private Funcionario funcionario;
	
	
	public Pessoa() {
	}

	public Pessoa(String cpf, String nome, Calendar dataNascimento) {
		this.setCpf(cpf);
		this.setNome(nome);
		this.setDataNascimento(dataNascimento);
	}

	public Pessoa(String rg, String cpf, String nome, Sexo sexo,
			Calendar dataNascimento) {
		this.setRg(rg);
		this.setCpf(cpf);
		this.setNome(nome);
		this.setSexo(sexo);
		this.setDataNascimento(dataNascimento);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return this.cpf + " - " + this.nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		return true;
	}

}
