
package com.shashank.phhub.auth.custom.dto;

/**
 * If @username and @password comes from the client, @accessToken will be null.
 * 
 * @authProvider is mandatory to come from client.
 */
public class LoginRequest {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginRequest [username=" + username + "]";
	}

}
