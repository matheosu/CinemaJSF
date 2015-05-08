package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class SecurityUtil {

	public static String criptografarSenha(String senha) {
		String senhaCP = senha;
		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("SHA-256");
			byte messageDigest[];
			messageDigest = algorithm.digest(senha.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				hexString.append(String.format("%02X", 0xFF & b));
			}
			senhaCP = hexString.toString();
		} catch (NoSuchAlgorithmException nsalE) {
			System.err.println("[SecurityUtil] Erro ao criptografar: " + nsalE.getMessage());
			nsalE.printStackTrace();
		} catch (UnsupportedEncodingException ueE) {
			System.err.println("[SecurityUtil] Erro ao criptografar: " + ueE.getMessage());
			ueE.printStackTrace();
		}

		return senhaCP;

	}
}
