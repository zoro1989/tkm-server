package com.ccbjb.common.exception;

/**
 * 
 * <p>Title: CustomException</p>
 * <p>Description: 系统自定义的异常类型，实际开发中可能要定义多种异常类型</p>
 * <p>Company: www.itcast.com</p> 
 * @author	传智.燕青
 * @date	2015-3-22下午2:50:30
 * @version 1.0
 */
public abstract class TKMBaseException extends RuntimeException {
	
	/**
	 * @fields serialVersionUID 
	 */
	private static final long serialVersionUID = -8349706232669896553L;
	
	/**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public TKMBaseException() {
        super();
    }
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
	public TKMBaseException(String message){
		super(message);
		
	}
    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     * @since  1.0
     */
    public TKMBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
