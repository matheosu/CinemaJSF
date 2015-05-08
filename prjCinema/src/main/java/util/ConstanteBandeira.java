package util;

public abstract class ConstanteBandeira {

	/* Número máximo pego pelo maior número maximo de cartão entre os enums*/
	private static final int TAMANHO_MAX_NUM_CARTAO = 16;
	
	/* Número máximo pego pelo maior número maximo de CVC entre os enums*/
	private static final int TAMANHO_MAX_CVC = 4;

	/* Quantidade mínima de número necessário para identificar a bandeira do cartão */
	public static final int TAMANHO_MIN_CARTAO = 4;
	
	/* VISA */
	public static final int[] VISA_COMECACOM = {4};
	public static final int[] VISA_TAMANHO_MAX_NUM = {13,16};
	public static final int VISA_TAMANHO_CVC = 3;
	
	/* MASTERCARD */
	public static final int[] MASTERCARD_COMECACOM = {5};
	public static final int[] MASTERCARD_TAMANHO_MAX_NUM = {16};
	public static final int MASTERCARD_TAMANHO_CVC = 3;
	
	/* AMEX (American Express) */
	public static final int[] AMEX_COMECACOM = {34, 37};
	public static final int[] AMEX_TAMANHO_MAX_NUM = {15};
	public static final int AMEX_TAMANHO_CVC = 4;

	/* Desconhecido */
	public static final int[] DESCONHECIDO_COMECACOM = {};
	public static final int[] DESCONHECIDO_TAMANHO_MAX_NUM ={TAMANHO_MAX_NUM_CARTAO};
	public static final int DESCONHECIDO_TAMANHO_CVC=TAMANHO_MAX_CVC;
}
