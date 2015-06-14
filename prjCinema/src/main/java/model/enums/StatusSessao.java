package model.enums;

public enum StatusSessao {

	CRIADA("Criada"),
	DISPONIVEL("Disponível"), 
	EXIBICAO("Exibição"),
	FINALIZADA("Finalizada");
	
	private String value;
	
	StatusSessao(String value){
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString(){
		return this.getValue();
	}
}
