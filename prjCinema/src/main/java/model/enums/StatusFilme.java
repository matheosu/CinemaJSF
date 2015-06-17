package model.enums;

public enum StatusFilme {
	
	LANCAMENTO("Lançamento"),
	EXIBICAO("Em Exibição");
	
	private String value;
	
	StatusFilme(String value){
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
		return this.value;
	}
}
