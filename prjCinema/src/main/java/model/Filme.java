package model;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import model.enums.Classificacao;
import model.enums.StatusFilme;

@Entity
@Table(name = "filmes")
public class Filme implements BaseModel {

	private static final long serialVersionUID = 9060234903652772597L;
	
	public static final int MAX_LENGTH_DIRECAO = 100;
	public static final int MAX_LENGTH_TITULO = 150;
	public static final int MAX_LENGTH_URL = 500;
	public static final int MAX_LENGTH_SINOPSE = 1000;

	@Id
	@GeneratedValue(generator = "FILME_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FILME_ID", sequenceName = "SEQ_FILME", allocationSize = 1)
	private Long id;

	@Column(nullable = false, unique = true, length = MAX_LENGTH_TITULO)
	private String titulo;

	@Column(nullable = false)
	@Temporal(TemporalType.TIME)
	private Date duracao;

	@Column(length = MAX_LENGTH_SINOPSE)
	private String sinopse;

	@Column(nullable = false, length = MAX_LENGTH_DIRECAO)
	private String direcao;

	@Enumerated(EnumType.ORDINAL)
	private Classificacao classificacao;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private StatusFilme status;

	@Lob
	@Basic(fetch = FetchType.LAZY, optional = true)
	private Byte[] imagem;

	@Column(length = MAX_LENGTH_URL)
	private URL trailer;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "filme")
	private List<Sessao> sessoes;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<Genero> generos;

	public Filme() {
		this.initCollections();
	}

	public Filme(String titulo, String direcao, Date duracao,
			Classificacao classificacao, StatusFilme status) {
		this.setTitulo(titulo);
		this.setDirecao(direcao);
		this.setDuracao(duracao);
		this.setClassificacao(classificacao);
		this.setStatus(status);
		this.initCollections();
	}

	public Filme(String titulo, Date duracao, String sinopse, String direcao,
			Classificacao classificacao, StatusFilme status, Byte[] imagem,
			URL trailer) {
		this.setTitulo(titulo);
		this.setDuracao(duracao);
		this.setSinopse(sinopse);
		this.setDirecao(direcao);
		this.setClassificacao(classificacao);
		this.setStatus(status);
		this.setImagem(imagem);
		this.setTrailer(trailer);
		this.initCollections();
	}

	private void initCollections() {
		this.setSessoes(new ArrayList<Sessao>());
		this.setGeneros(new ArrayList<Genero>());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDuracao() {
		return duracao;
	}

	public void setDuracao(Date duracao) {
		this.duracao = duracao;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

	public Classificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(Classificacao classificacao) {
		this.classificacao = classificacao;
	}

	public StatusFilme getStatus() {
		return status;
	}

	public void setStatus(StatusFilme status) {
		this.status = status;
	}

	public Byte[] getImagem() {
		return imagem;
	}

	public void setImagem(Byte[] imagem) {
		this.imagem = imagem;
	}

	public URL getTrailer() {
		return trailer;
	}

	public void setTrailer(URL trailer) {
		this.trailer = trailer;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public int getMaxLengthTitulo() {
		return MAX_LENGTH_TITULO;
	}

	public int getMaxLengthSinopse() {
		return MAX_LENGTH_SINOPSE;
	}

	public int getMaxLengthDirecao() {
		return MAX_LENGTH_DIRECAO;
	}

	@Override
	public String toString() {
		return titulo + " - " + direcao + "[" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Filme other = (Filme) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
