package com.trantuandung.technictest.exception;

public class RequestErrorException extends Exception{
    public RequestErrorException() {
    }

    /**
     * Constructs a new {@code RequestTypeNotFoundException} with its stack trace and detail
     * message filled in.
     *
     * @param detailMessage
     *            the detail message for this exception.
     */
    public RequestErrorException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs a new instance of this class with detail message and cause
     * filled in.
     *
     * @param message
     *            The detail message for the exception.
     * @param cause
     *            The detail cause for the exception.
     * @since 1.6
     */
    public RequestErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new instance of this class with its detail cause filled in.
     *
     * @param cause
     *            The detail cause for the exception.
     * @since 1.6
     */
    public RequestErrorException(Throwable cause) {
        super(cause == null ? null : cause.toString(), cause);
    }
}
