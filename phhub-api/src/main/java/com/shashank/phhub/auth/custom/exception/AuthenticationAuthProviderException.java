
package com.shashank.phhub.auth.custom.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationAuthProviderException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	private static final String MESSAGE_FORMAT = "AuthenticationAuthProviderException: '%s'";

	public AuthenticationAuthProviderException(String msg) {
		super(String.format(MESSAGE_FORMAT, msg));

	}

}
