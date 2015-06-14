package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "salas")
public class Sala extends BaseModel{

	@Id
	@GeneratedValue(generator = "SALA_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SALA_ID", sequenceName = "SEQ_SALA", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private Integer numero;
	
	@Column(nullable = false)
	private Integer capacidade;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sala")
	private List<Sessao> sessoes;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sala")
	private List<Lugar> lugares;

	public Sala() {
		this.initCollections();
	}

	public Sala(Integer numero, Integer capacidade) {
		this.setNumero(numero);
		this.setCapacidade(capacidade);
		this.initCollections();
	}

	private void initCollections() {
		this.setSessoes(new ArrayList<Sessao>());
		this.setLugares(new ArrayList<Lugar>());
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

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessao) {
		this.sessoes = sessao;
	}

	public List<Lugar> getLugares() {
		return lugares;
	}

	public void setLugares(List<Lugar> lugar) {
		this.lugares = lugar;
	}

	public int getQuantidadeLugares(){
		return this.getLugares().size();
	}
	
	@Override
	public String toString() {
		return numero + "[" + capacidade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((capacidade == null) ? 0 : capacidade.hashCode());
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
		Sala other = (Sala) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (capacidade == null) {
			if (other.capacidade != null)
				return false;
		} else if (!capacidade.equals(other.capacidade))
			return false;

		return true;
	}

}
