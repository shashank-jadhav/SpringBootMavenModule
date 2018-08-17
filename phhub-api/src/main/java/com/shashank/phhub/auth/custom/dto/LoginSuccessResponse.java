
package com.shashank.phhub.auth.custom.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginSuccessResponse implements Serializable {

	private static final long serialVersionUID = -955313357369346482L;

	private Long userId;
	private String username;
	private boolean isProfileUpdated;
	private String role;

}
