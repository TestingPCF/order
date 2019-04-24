package com.hcl.cloud.order.exception;

/**
 * This is custom exception, which can be used in case of 400.
 * @author shikhar.a || ankit-kumar
 */
public class BadRequestException extends RuntimeException {

	/**
	 * BadRequestException.
	 * @param message
	 *            Message
	 */
	public BadRequestException(final String message) {
		super(message);
	}

	/**
	 * BadRequestException.
	 * @param message
	 *            Message
	 * @param cause
	 *            Exception Object
	 */
	public BadRequestException(final String message,
			final Throwable cause) {
		super(message, cause);
	}

}
