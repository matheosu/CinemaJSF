package model.enums;

import util.ConstanteBandeira;

public enum Bandeira {

	VISA(ConstanteBandeira.VISA_COMECACOM, ConstanteBandeira.VISA_TAMANHO_MAX_NUM, ConstanteBandeira.VISA_TAMANHO_CVC),
	MASTERCARD(ConstanteBandeira.MASTERCARD_COMECACOM, ConstanteBandeira.MASTERCARD_TAMANHO_MAX_NUM, ConstanteBandeira.MASTERCARD_TAMANHO_CVC),
	AMEX(ConstanteBandeira.AMEX_COMECACOM, ConstanteBandeira.AMEX_TAMANHO_MAX_NUM, ConstanteBandeira.AMEX_TAMANHO_CVC),
	DESCONHECIDO(ConstanteBandeira.DESCONHECIDO_COMECACOM, ConstanteBandeira.DESCONHECIDO_TAMANHO_MAX_NUM, ConstanteBandeira.DESCONHECIDO_TAMANHO_CVC);
	
	private int[] comecaCom;
	private int[] numeroMaximo;
	private int numeroMaximoCVC;
	
	
	Bandeira(int[] comecaCom, int[] numeroMaximo, int numeroMaximoCVC){
		this.setComecaCom(comecaCom);
		this.setNumeroMaximo(numeroMaximo);
		this.setNumeroMaximoCVC(numeroMaximoCVC);
	}

	public int[] getComecaCom() {
		return comecaCom;
	}


	public void setComecaCom(int[] comecaCom) {
			this.comecaCom = comecaCom;
	}
	
	public int[] getNumeroMaximo() {
		return numeroMaximo;
	}


	public void setNumeroMaximo(int[] numeroMaximo) {
		this.numeroMaximo = numeroMaximo;
	}


	public int getNumeroMaximoCVC() {
		return numeroMaximoCVC;
	}


	public void setNumeroMaximoCVC(int numeroMaximoCVC) {
		this.numeroMaximoCVC = numeroMaximoCVC;
	}

	@Override
	public String toString(){
		return this.name();
	}
}
