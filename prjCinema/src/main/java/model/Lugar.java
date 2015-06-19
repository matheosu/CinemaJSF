package model;

import java.util.ArrayList;
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

@Entity
@Table(name = "lugares")
public class Lugar implements BaseModel{

	@Id
	@GeneratedValue(generator = "LUGAR_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "LUGAR_ID", sequenceName = "SEQ_LUGAR", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	private Character fila;

	@Column(nullable = false)
	private Integer numero;

	@ManyToOne
	private Sala sala;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
	private List<Ingresso> ingressos;

	private boolean especial;

	public Lugar() {
		this.initCollections();
	}

	public Lugar(Sala sala, Character fila, Integer numero) {
		this.setSala(sala);
		this.setFila(fila);
		this.setNumero(numero);
		this.initCollections();
	}

	public Lugar(Sala sala, Character fila, Integer numero, boolean especial) {
		this.setSala(sala);
		this.setFila(fila);
		this.setNumero(numero);
		this.setEspecial(especial);
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

	public Character getFila() {
		return fila;
	}

	public void setFila(Character fila) {
		this.fila = fila;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public boolean isEspecial() {
		return especial;
	}

	public void setEspecial(boolean especial) {
		this.especial = especial;
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

	@Override
	public String toString() {
		return fila + "" + numero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((fila == null) ? 0 : fila.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((sala == null) ? 0 : sala.hashCode());
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
		Lugar other = (Lugar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (fila == null) {
			if (other.fila != null)
				return false;
		} else if (!fila.equals(other.fila))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (sala == null) {
			if (other.sala != null)
				return false;
		} else if (!sala.equals(other.sala))
			return false;
		return true;
	}

}
