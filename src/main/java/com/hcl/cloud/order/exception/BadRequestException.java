package com.hcl.cloud.order.exception;

/**
 * This is custom exception, which can be used in case of 400.
 * @author shikhar.a || ankit-kumar
 */
public class BadRequestException extends RuntimeException {

    /**
     * BadRequestException.
     * @param message
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * BadRequestException.
     * @param message
     * @param cause
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
