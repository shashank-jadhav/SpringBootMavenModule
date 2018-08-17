
package com.shashank.phhub.auth.custom.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Represents format not acceptable Type appears for JSON or other data format parsing during 
 * authentication process.
 */
public class AuthenticationFormatNotAcceptableException extends
		AuthenticationException {

private static final long serialVersionUID = 7002048499899584526L;
	
	// ~ Constructors
	// ===================================================================================================

	/**
	 * Constructs a <code>AuthenticationFormatNotAcceptableException</code> with the
	 * specified message.
	 *
	 * @param msg
	 *            the detail message.
	 */
	public AuthenticationFormatNotAcceptableException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>AuthenticationFormatNotAcceptableException</code> with the
	 * specified message and root cause.
	 *
	 * @param msg
	 *            the detail message.
	 * @param t
	 *            root cause
	 */
	public AuthenticationFormatNotAcceptableException(String msg, Throwable t) {
		super(msg, t);
	}

}
