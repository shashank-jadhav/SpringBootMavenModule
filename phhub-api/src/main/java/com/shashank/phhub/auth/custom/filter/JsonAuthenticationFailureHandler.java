package com.shashank.phhub.auth.custom.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.shashank.phhub.base.common.enums.Errors;
import com.shashank.phhub.core.dto.GenericResponse;

public class JsonAuthenticationFailureHandler implements AuthenticationFailureHandler {

	public JsonAuthenticationFailureHandler() {
	}

	@Autowired
	private MessageSource messageSource;

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		GenericResponse res;

		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		// logic to get the domain specific status from exception if exist

		if (exception instanceof LockedException) {

			res = new GenericResponse.Builder().error(Errors.AccountLocked)
					.message(messageSource.getMessage("Errors.AccountLocked.message", null, null, null)).build();
		} else if (exception instanceof DisabledException) {

			res = new GenericResponse.Builder().error(Errors.AccountDisabled)
					.message(messageSource.getMessage("Errors.AccountDisabled.message", null, null, null)).build();
		} else if (exception instanceof BadCredentialsException) {
			res = new GenericResponse.Builder().error(Errors.BadCredentials)
					.message(messageSource.getMessage("Errors.BadCredentials.message", null, null, null)).build();
		} else if (exception instanceof UsernameNotFoundException) {
			res = new GenericResponse.Builder().error(Errors.UsernameNotFound)
					.message(messageSource.getMessage("Errors.UsernameNotFound.message", null, null, null)).build();
		} else {

			res = new GenericResponse.Builder().error(Errors.BadCredentials)
					.message(messageSource.getMessage("Errors.BadCredentials.message", null, null, null)).build();
		}

		JsonAuthenticationResponseWriterUtil.writeJsonModelToHttpServletResponse(response, res);

	}

}
