package model.enums;

public enum NivelSetor {
	
	CONTROLE("Controle"), 
	OPERACAO("Operação"), 
	ADMINISTRACAO("Administração"); 
	
	private String descricao;
	
	
	NivelSetor(String descricao){
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
		return this.getDescricao();
	}
	
}
