
package com.shashank.phhub.security.domain;


/**
 * This class holds the Role enumerations which are provided during registration
 * to determine the type of the user.
 */
public enum Role {
	
	ROLE_NORMAL_USER,
	ROLE_ADMIN_USER,
	
	;

	public static boolean contains(String test) {

		for (Role c : Role.values()) {
			if (c.name().equals(test)) {
				return true;
			}
		}
		return false;
	}

}
