package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import model.enums.NivelSetor;

@Entity
@Table(name = "setores")
public class Setor extends BaseModel{

	public static final int MAX_LENGTH_DESCRICAO = 100;

	@Id
	@GeneratedValue(generator = "SETOR_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "SETOR_ID", sequenceName = "SEQ_SETOR", allocationSize = 1)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private NivelSetor nivel;
	
	@Column(nullable = false, unique = true, length = MAX_LENGTH_DESCRICAO)
	private String descricao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "setor")
	private List<Funcionario> funcionarios;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_gerente",referencedColumnName="id_gerente")
	private Funcionario gerente;

	public Setor() {
		this.initCollections();
	}

	public Setor(NivelSetor nivel, String descricao) {
		this.setNivel(nivel);
		this.setDescricao(descricao);
		this.initCollections();
	}

	private void initCollections() {
		this.setFuncionarios(new ArrayList<Funcionario>());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NivelSetor getNivel() {
		return nivel;
	}

	public void setNivel(NivelSetor nivel) {
		this.nivel = nivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Funcionario getGerente() {
		return gerente;
	}

	public void setGerente(Funcionario gerente) {
		this.gerente = gerente;
	}

	@Override
	public String toString() {
		return descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
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
		Setor other = (Setor) obj;
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
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		return true;
	}

}
