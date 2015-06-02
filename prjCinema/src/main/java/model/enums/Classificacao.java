package model.enums;

public enum Classificacao {
	
	LIVRE(0,"Livre"),
	DOZE_ANOS(12,"12 Anos"),
	QUATORZE_ANOS(14,"14 Anos"),
	DEZESSEIS_ANOS(16, "16 Anos"),
	DEZOITO_ANOS(18, "18 Anos");
	
	private static final int MIN_VALUE = 0;
	private static final int MAX_VALUE = 18;
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
		if(this.validValue(idade))
			this.idade = idade;
	}

	private boolean validValue(int value) {
		return value >= MIN_VALUE && value <= MAX_VALUE;
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
