package model.enums;

public enum Sexo {

	FEMININO("Feminino"), MASCULINO("Masculino");
	
	private String descricao;
	
	Sexo(String descricao){
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
