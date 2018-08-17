
package com.shashank.phhub.security.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * <b>Purpose:</b> This class represents core user details which is passed to
 * User. Spring security authenticates the user based on this principal that it
 * obtains from here.
 * <p>
 * If account is not enabled or is locked then authenticationProvider(i.e.
 * AbstractUserDetailsAuthenticationProvider) will throw DisabledException and
 * LockedException respectively, these need to be caught in
 * AuthenticationFailureHandler to write custom domain exception status.
 * </p>
 *
 */

public class CustomSecurityUserDetails extends User {

	private static final long serialVersionUID = 1L;

	private Long userId;
	private String username;
	private String authProvider;
	private String role;
	private boolean isProfileUpdated;

	private CustomSecurityUserDetails(String username, String password, boolean activated, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNotBanned,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, activated, accountNonExpired, credentialsNonExpired, accountNotBanned, authorities);
	}

	/**
	 * @Builder is used to build the SecurityUserDetails i.e. @this class
	 */
	public static Builder getBuilder() {
		return new Builder();
	}

	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public String getAuthProvider() {
		return authProvider;
	}

	public String getRole() {
		return role;
	}

	public boolean isProfileUpdated() {
		return isProfileUpdated;
	}

	public static class Builder {

		private Long userId;
		private String username;
		private String password;
		private String authProvider;
		private String role;
		private boolean isProfileUpdated;

		private Set<GrantedAuthority> authorities;
		private boolean accountNotBanned;
		private boolean activated;

		private Builder() {
			this.authorities = new HashSet<GrantedAuthority>();
		}

		public CustomSecurityUserDetails build() {

			CustomSecurityUserDetails securityUserDetails = new CustomSecurityUserDetails(username, password, activated,
					true, true, accountNotBanned, authorities);

			securityUserDetails.userId = userId;
			securityUserDetails.username = username;
			securityUserDetails.role = role;
			securityUserDetails.authProvider = authProvider;
			securityUserDetails.isProfileUpdated = isProfileUpdated;
			return securityUserDetails;
		}

		public Builder userId(Long userId) {
			this.userId = userId;
			return this;
		}

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder authProvider(String authProvider) {
			this.authProvider = authProvider;
			return this;
		}

		public Builder role(String role) {
			this.role = role;
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			this.authorities.add(authority);
			return this;
		}

		public Builder accountNotBanned(boolean accountNotBanned) {
			this.accountNotBanned = accountNotBanned;
			return this;
		}

		public Builder activated(boolean activated) {
			this.activated = activated;
			return this;
		}

		public Builder isProfileUpdated(boolean isProfileUpdated) {
			this.isProfileUpdated = isProfileUpdated;
			return this;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomSecurityUserDetails other = (CustomSecurityUserDetails) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	// formatter:off
	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("CustomSecurityUserDetails [userId=");
		builder2.append(userId);
		builder2.append(", role=");
		builder2.append(role);
		builder2.append(", username=");
		builder2.append(username);
		builder2.append("]");
		return builder2.toString();
	}
	// formatter:on

}
