package exception;

import util.ExceptionUtil;

public class ModelException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String NAME_EXCEPTION = ModelException.class.getSimpleName();
	
	public ModelException() {
		super();
	}
	
	public ModelException(String msg){
		super(ExceptionUtil.msgException(NAME_EXCEPTION, msg));
	}

	public ModelException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(ExceptionUtil.msgException(NAME_EXCEPTION, message), cause, enableSuppression, writableStackTrace);
	}

	public ModelException(String message, Throwable cause) {
		super(ExceptionUtil.msgException(NAME_EXCEPTION, message), cause);
	}

	public ModelException(Throwable cause) {
		super(cause);
	}

	
}
