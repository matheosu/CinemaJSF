package exception;

import util.ExceptionUtil;

public class BeanException extends Exception{
	private static final long serialVersionUID = 8663873908480215072L;
	private static final String NAME_EXCEPTION = BeanException.class.getSimpleName();
	public BeanException() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BeanException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(ExceptionUtil.msgException(NAME_EXCEPTION, message), cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
	public BeanException(String message, Throwable cause) {
		super(ExceptionUtil.msgException(NAME_EXCEPTION, message), cause);
		// TODO Auto-generated constructor stub
	}
	public BeanException(String message) {
		super(ExceptionUtil.msgException(NAME_EXCEPTION, message));
		// TODO Auto-generated constructor stub
	}
	public BeanException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
