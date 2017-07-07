package com.ccbjb.common.exception;

/**
 * Razor属性检查功能模块自定义异常类
 * @author CJB
 * @since 1.0
 */
public class ValidateException extends TKMBaseException {

	/**
	 * @fields serialVersionUID 
	 */
	private static final long serialVersionUID = -6407586972327707046L;
	/**
	 * Constructs a new UtilityException exception with {@code null} as its
	 * detail message. The cause is not initialized, and may subsequently be
	 * initialized by a call to {@link #initCause}.
	 */
	public ValidateException() {
		super();
	}

	/**
	 * Constructs a new runtime exception with the specified detail message. The
	 * cause is not initialized, and may subsequently be initialized by a call
	 * to {@link #initCause}.
	 * 
	 * @param message
	 *            the detail message. The detail message is saved for later
	 *            retrieval by the {@link #getMessage()} method.
	 */
	public ValidateException(String message) {
		super(message);
	}
}

