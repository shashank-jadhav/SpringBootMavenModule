
package com.shashank.phhub.auth.custom.exception;

import org.springframework.security.core.AuthenticationException;

public class SameUsernamePasswordAuthException extends AuthenticationException {

	private static final long serialVersionUID = 7002048499899584526L;
	
	// ~ Constructors
	// ===================================================================================================

	/**
	 * Constructs a <code>SameUsernamePasswordException</code> with the
	 * specified message.
	 *
	 * @param msg
	 *            the detail message.
	 */
	public SameUsernamePasswordAuthException(String msg) {
		super(msg);
	}

	/**
	 * Constructs a <code>SameUsernamePasswordException</code> with the
	 * specified message and root cause.
	 *
	 * @param msg
	 *            the detail message.
	 * @param t
	 *            root cause
	 */
	public SameUsernamePasswordAuthException(String msg, Throwable t) {
		super(msg, t);
	}

}
