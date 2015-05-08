package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sessoes")
public class Sessao extends BaseModel{

	@Id
	@GeneratedValue(generator = "SESSAO_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SESSAO_ID", sequenceName = "SEQ_SESSAO", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataHora;

	@Column(nullable = false)
	private float valor;

	@Temporal(TemporalType.DATE)
	private Calendar validade;

	@ManyToOne
	private Sala sala;
	
	@ManyToOne
	private Filme filme;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sessao")
	private List<Ingresso> ingressos;

	public Sessao() {
		this.initCollections();
	}

	public Sessao(Filme filme, Sala sala, Calendar dataHora, float valor,
			Calendar validade) {
		this.setFilme(filme);
		this.setSala(sala);
		this.setDataHora(dataHora);
		this.setValor(valor);
		this.setValidade(validade);
		this.initCollections();
	}

	private void initCollections() {
		this.setIngressos(new ArrayList<Ingresso>());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public Calendar getValidade() {
		return validade;
	}

	public void setValidade(Calendar validade) {
		this.validade = validade;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	@Override
	public String toString() {
		return "Data/Hora: " + dataHora + " - " + "Sala: " + sala
				+ " - " + "Filme: " + filme;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((filme == null) ? 0 : filme.hashCode());
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
		result = prime * result
				+ ((dataHora == null) ? 0 : dataHora.hashCode());
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
		Sessao other = (Sessao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (filme == null) {
			if (other.filme != null)
				return false;
		} else if (!filme.equals(other.filme))
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;

		return true;
	}

}
