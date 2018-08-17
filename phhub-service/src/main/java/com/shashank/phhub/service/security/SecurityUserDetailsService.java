
package com.shashank.phhub.service.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shashank.phhub.core.dao.UserRepository;
import com.shashank.phhub.hib.model.user.User;
import com.shashank.phhub.security.domain.AccountStatus;
import com.shashank.phhub.security.domain.CustomSecurityUserDetails;

@Component
public class SecurityUserDetailsService implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(SecurityUserDetailsService.class);

	@Autowired
	private UserRepository userDao;

	public SecurityUserDetailsService() {
	}

	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Inside SecurityUserDetailsService#loadUserByUsername() for username: {}", username);

		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not registered");
		}

		CustomSecurityUserDetails principal = CustomSecurityUserDetails.getBuilder().userId(user.getUserId())
				.username(user.getUsername()).authProvider(user.getAuthProvider()).role(user.getRole())
				.isProfileUpdated(user.isProfileUpdated()).accountNotBanned(!user.isBanned())
				.activated(user.getAccountStatus().equals(AccountStatus.ACTIVE.name())).password(user.getPasswordHash())
				.build();
		return principal;
	}
}