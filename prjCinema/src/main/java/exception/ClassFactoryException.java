package exception;

public class ClassFactoryException extends Exception {

	private static final long serialVersionUID = -3574724678310042200L;
	private static final String TITLE_MSG = "[" + ClassFactoryException.class.getSimpleName() + "] :";
	
	
	public ClassFactoryException() {
		super();
	}

	public ClassFactoryException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(TITLE_MSG + message, cause, enableSuppression, writableStackTrace);
	}

	public ClassFactoryException(String message, Throwable cause) {
		super(TITLE_MSG + message, cause);
	}

	public ClassFactoryException(String message) {
		super(TITLE_MSG + message);
	}

	public ClassFactoryException(Throwable cause) {
		super(cause);
	}

	
}
