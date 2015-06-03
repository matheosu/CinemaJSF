package model.enums;

public enum Classificacao {
	
	LIVRE(0,"Livre"),
	DOZE_ANOS(12,"12 Anos"),
	QUATORZE_ANOS(14,"14 Anos"),
	DEZESSEIS_ANOS(16, "16 Anos"),
	DEZOITO_ANOS(18, "18 Anos");
	
	private int idade;
	private String descricao;
	
	Classificacao(int idade, String descricao){
		this.setIdade(idade);
		this.setDescricao(descricao);
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
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
