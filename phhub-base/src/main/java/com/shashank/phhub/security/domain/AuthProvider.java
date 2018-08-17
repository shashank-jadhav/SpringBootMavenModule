
package com.shashank.phhub.security.domain;


/**
 * This class holds the Role enumerations which are provided during registration
 * to determine the type of the user.
 */
public enum AuthProvider {
	
	AUP_APPLICATION,
	
	;

	public static boolean contains(String test) {

		for (AuthProvider c : AuthProvider.values()) {
			if (c.name().equals(test)) {
				return true;
			}
		}
		return false;
	}

}
