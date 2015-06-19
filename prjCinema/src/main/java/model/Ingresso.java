package model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ingressos")
public class Ingresso implements BaseModel{
	
	@Id
	@GeneratedValue(generator = "INGRESSO_ID", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "INGRESSO_ID", sequenceName = "SEQ_INGRESSO", allocationSize = 1)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Calendar dataHora;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Sessao sessao;
	
	@ManyToOne
	private Lugar lugar;
	
	@ManyToOne
	private Desconto desconto;

	public Ingresso(){}
	
	public Ingresso(Sessao sessao, Cliente cliente, Lugar lugar){
		this.setSessao(sessao);
		this.setCliente(cliente);
		this.setLugar(lugar);
		this.setDataHora(Calendar.getInstance());
	}
	
	public Ingresso(Sessao sessao, Cliente cliente, Lugar lugar, Desconto desconto){
		this.setSessao(sessao);
		this.setCliente(cliente);
		this.setLugar(lugar);
		this.setDesconto(desconto);
		this.setDataHora(Calendar.getInstance());
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Sessao getSessao() {
		return sessao;
	}
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	public Desconto getDesconto() {
		return desconto;
	}
	public void setDesconto(Desconto desconto) {
		this.desconto = desconto;
	}
	public Lugar getLugar() {
		return lugar;
	}
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
	
	@Override
	public String toString() {
		return "Data/Hora: " + dataHora + " - " 
				+ "Cliente: " + cliente + " - "
				+ "Sessao: " + sessao + " - "
				+ "Lugar: " + lugar;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((sessao == null) ? 0 : sessao.hashCode());
		result = prime * result + ((lugar == null) ? 0 : lugar.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		Ingresso other = (Ingresso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataHora == null) {
			if (other.dataHora != null)
				return false;
		} else if (!dataHora.equals(other.dataHora))
			return false;
		if (lugar == null) {
			if (other.lugar != null)
				return false;
		} else if (!lugar.equals(other.lugar))
			return false;
		if (sessao == null) {
			if (other.sessao != null)
				return false;
		} else if (!sessao.equals(other.sessao))
			return false;
		return true;
	}

	
	
}
