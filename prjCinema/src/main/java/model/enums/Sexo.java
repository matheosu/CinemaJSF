package model.enums;

public enum Sexo {

	FEMININO("Feminino"), MASCULINO("Masculino");
	
	public static final int MAX_LENGHT = 15;
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
	
	@Override
	public String toString(){
		return this.descricao;
	}
	
	
}
