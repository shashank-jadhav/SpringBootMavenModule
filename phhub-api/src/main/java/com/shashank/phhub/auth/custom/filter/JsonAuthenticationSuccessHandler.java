package com.shashank.phhub.auth.custom.filter;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.shashank.phhub.auth.custom.dto.LoginSuccessResponse;
import com.shashank.phhub.base.common.enums.Results;
import com.shashank.phhub.core.dto.GenericResponse;
import com.shashank.phhub.security.domain.CustomSecurityUserDetails;

public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	public JsonAuthenticationSuccessHandler() {
		super();
	}

	@Autowired
	private MessageSource messageSource;

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException, AuthenticationServiceException {

		LoginSuccessResponse loginSuccessResponse = new LoginSuccessResponse();

		Object principal = authentication.getPrincipal();
		// get the details from authentication and put into loginResponse.

		if (principal instanceof CustomSecurityUserDetails) {
			CustomSecurityUserDetails securityUserDetails = (CustomSecurityUserDetails) principal;

			loginSuccessResponse.setUserId(securityUserDetails.getUserId());
			loginSuccessResponse.setUsername(securityUserDetails.getUsername());
			loginSuccessResponse.setRole(securityUserDetails.getRole());
			loginSuccessResponse.setProfileUpdated(securityUserDetails.isProfileUpdated());

			GenericResponse resp = new GenericResponse.Builder().result(Results.LoginSuccess)
					.message(messageSource.getMessage("Success.LoginSuccess.message", null, null, null))
					.data(loginSuccessResponse).build();
			try {
				response.setContentType("application/json");
				response.setStatus(HttpServletResponse.SC_OK);
				JsonAuthenticationResponseWriterUtil.writeJsonModelToHttpServletResponse(response, resp);
			} catch (AuthenticationServiceException e) {
				request.getSession().invalidate();
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
			}
		}
	}
}
