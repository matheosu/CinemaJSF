package util;

public class ExceptionUtil{
	
	private static final String separator = ":";

	public static String msgException(String nameException, String msg) {
		String finalMsg = "[" + nameException + " - ";

		if (msg != null && !msg.isEmpty()) {
			String[] array = null;
			if (msg.contains(separator)) {
				array = msg.split(separator);
				finalMsg += array[0];
				finalMsg += "]";
				finalMsg += " " + array[1];
			} else {
				finalMsg += "]";
				finalMsg += msg;
			}
		} else {
			finalMsg += "]";
		}

		return finalMsg;

	}

}
