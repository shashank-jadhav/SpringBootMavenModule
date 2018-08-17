package com.shashank.phhub.auth.custom.filter;



import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shashank.phhub.auth.custom.dto.LoginRequest;
import com.shashank.phhub.auth.custom.exception.AuthenticationFormatNotAcceptableException;
import com.shashank.phhub.auth.custom.exception.SameUsernamePasswordAuthException;

/**
 * This class will help picking the custom authentication data from the
 * HttpServletRequest. Here we have two choices to implement. If we want to go
 * with post parameters, then we need to simply override
 * {@code protected String obtainUsername(HttpServletRequest request);} and
 * {@code protected String obtainPassword(HttpServletRequest request);} methods.
 * Here we can simply extract some another authentication related parameters
 * from HttpServletRequest.
 * 
 * If we want to use Json Payload to be parsed we need to override
 * {@code  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response);}
 */
public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public JsonUsernamePasswordAuthenticationFilter() {
	}

	Logger logger = LoggerFactory.getLogger(JsonUsernamePasswordAuthenticationFilter.class);

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		LoginRequest loginRequest = this.getLoginRequest(request);
		logger.info("Inside #attemptAuthentication() for username: {}", loginRequest.getUsername());

		UsernamePasswordAuthenticationToken authRequest = null;

		// username and Password equality check is required to avoid login
		// using facebook userId as registered user(placing both userId and
		// password as facebookId).
		if (loginRequest.getUsername().equals(loginRequest.getPassword())) {
			throw new SameUsernamePasswordAuthException("Username and Password cannot be same!");
		}

		authRequest = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private LoginRequest getLoginRequest(HttpServletRequest request) {
		BufferedReader reader = null;
		LoginRequest loginRequest = null;

		ObjectMapper jsonMapper = new ObjectMapper();
		try {
			reader = request.getReader();
			loginRequest = jsonMapper.readValue(reader, LoginRequest.class);
		} catch (JsonParseException e) {
			throw new AuthenticationFormatNotAcceptableException("JsonParserException " + e.getMessage());
		} catch (JsonMappingException e) {
			throw new AuthenticationFormatNotAcceptableException("JsonMappingException " + e.getMessage());
		} catch (IOException e) {
			throw new AuthenticationFormatNotAcceptableException("HttpRequest Reader " + e.getMessage());
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				logger.error("Exception Ocuured while closing the reader", ex);
			}
		}

		if (loginRequest == null) {
			loginRequest = new LoginRequest();
		}

		return loginRequest;
	}
}