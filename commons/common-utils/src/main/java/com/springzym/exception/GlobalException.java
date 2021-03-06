package com.springzym.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义异常类
 * @author springzym
 */
public class GlobalException extends RuntimeException {

    /**
     * 日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public GlobalException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public GlobalException(String message) {
        super(message);
        logger.error(message);
    }

    /**
     * Constructs a new runtime exception with the specified cause and a
     * detail message of <tt>(cause==null ? null : cause.toString())</tt>
     * (which typically contains the class and detail message of
     * <tt>cause</tt>).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method).  (A <tt>null</tt> value is
     *              permitted, and indicates that the cause is nonexistent or
     *              unknown.)
     * @since 1.4
     */
    public GlobalException(Throwable cause) {
        super(cause.getMessage());
        logger.error(cause.getMessage());
    }
}
