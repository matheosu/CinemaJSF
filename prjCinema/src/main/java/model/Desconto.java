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
@Table(name = "descontos")
public class Desconto implements BaseModel{

	@Id
	@GeneratedValue(generator = "DESCONTO_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "DESCONTO_ID", sequenceName = "SEQ_DESCONTO", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false, unique = true)
	private String descricao;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="desconto")
	private List<Ingresso> ingressos;

	public Desconto() {
		this.initCollections();
	}

	public Desconto(String descricao, Double valor) {
		this.setDescricao(descricao);
		this.setValor(valor);
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

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	@Override
	public String toString() {
		return descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Desconto other = (Desconto) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
