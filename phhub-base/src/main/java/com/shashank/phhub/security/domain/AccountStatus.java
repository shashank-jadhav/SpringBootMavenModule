
package com.shashank.phhub.security.domain;

/**
 * This class holds the Role enumerations which are provided during registration
 * to determine the type of the user.
 */
public enum AccountStatus {

	ACTIVE,
	PENDING_VERIFICATION,

	;

	public static boolean contains(String test) {

		for (AccountStatus c : AccountStatus.values()) {
			if (c.name().equals(test)) {
				return true;
			}
		}
		return false;
	}

}
