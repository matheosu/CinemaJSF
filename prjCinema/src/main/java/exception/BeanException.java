package exception;

import util.ExceptionUtil;

public class BeanException extends Exception{
	private static final long serialVersionUID = 8663873908480215072L;
	private static final String NAME_EXCEPTION = BeanException.class.getSimpleName();
	public BeanException() {
		super();
	}
	public BeanException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(ExceptionUtil.msgException(NAME_EXCEPTION, message), cause, enableSuppression, writableStackTrace);
	}
	public BeanException(String message, Throwable cause) {
		super(ExceptionUtil.msgException(NAME_EXCEPTION, message), cause);
	}
	public BeanException(String message) {
		super(ExceptionUtil.msgException(NAME_EXCEPTION, message));
	}
	public BeanException(Throwable cause) {
		super(cause);
	}

	
}
