package model.enums;

public enum StatusFilme {
	
	EM_CARTAZ("Em Cartaz"),
	EM_BREVE("Em Breve");
	
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
