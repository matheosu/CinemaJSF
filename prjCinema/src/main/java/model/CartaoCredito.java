package model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.enums.Bandeira;

@Entity
@Table(name="cartoesCredito")
public class CartaoCredito extends BaseModel{

	public static final int MAX_LENGTH_NOME_CARTAO_CREDITO = 200;
	
	@Id
	@GeneratedValue(generator = "CARTAOCREDITO_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "CARTAOCREDITO_ID", sequenceName = "SEQ_CARTAOCREDITO", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private Integer numero;
	
	@Column(nullable = false, length=MAX_LENGTH_NOME_CARTAO_CREDITO)
	private String nome;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar validade;
	
	@Enumerated(EnumType.STRING)
	private Bandeira bandeira;
	
	@ManyToOne
	private Cliente cliente;

	public CartaoCredito() {}

	public CartaoCredito(Cliente cliente, Integer numero, Calendar validade,
			String nomeImpresso) {
		this.setCliente(cliente);
		this.setNumero(numero);
		this.setValidade(validade);
		this.setNome(nomeImpresso);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getValidade() {
		return validade;
	}

	public void setValidade(Calendar validade) {
		this.validade = validade;
	}

	public Bandeira getBandeira() {
		return bandeira;
	}

	public void setBandeira(Bandeira bandeira) {
		this.bandeira = bandeira;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public static Bandeira buscarBandeira(Integer numero) {
		while(numero < 9999){
			for (int i : Bandeira.VISA.getComecaCom()) {
				if (i == numero)
					return Bandeira.VISA;
			}
	
			for (int i : Bandeira.MASTERCARD.getComecaCom()) {
				if (i == numero)
					return Bandeira.MASTERCARD;
	
			}
	
			for (int i : Bandeira.AMEX.getComecaCom()) {
				if (i == numero)
					return Bandeira.AMEX;
			}
		}
		return Bandeira.DESCONHECIDO;
	}

	@Override
	public String toString() {
		return bandeira + " - " + numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((bandeira == null) ? 0 : bandeira.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((validade == null) ? 0 : validade.hashCode());
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
		CartaoCredito other = (CartaoCredito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (bandeira != other.bandeira)
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (validade == null) {
			if (other.validade != null)
				return false;
		} else if (!validade.equals(other.validade))
			return false;
		return true;
	}

	
}
