package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "generos")
public class Genero extends BaseModel{

	public static final int MAX_LENGTH_DESCRICAO = 100;

	@Id
	@GeneratedValue(generator = "GENERO_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "GENERO_ID", sequenceName = "SEQ_GENERO", allocationSize = 1)
	private Long id;
	
	@Column(nullable = false, unique = true, length = MAX_LENGTH_DESCRICAO)
	private String descricao;
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="generos")
	private List<Filme> filmes;

	public Genero() {
		this.initCollections();
	}

	public Genero(String descricao) {
		this.setDescricao(descricao);
		this.initCollections();
	}

	private void initCollections() {
		this.setFilmes(new ArrayList<Filme>());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}
	
	public int getQuantidadeFilmes(){
		return this.getFilmes().size();
	}
	
	public int getMaxLengthDescricao() {
		return MAX_LENGTH_DESCRICAO;
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
		Genero other = (Genero) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

}
