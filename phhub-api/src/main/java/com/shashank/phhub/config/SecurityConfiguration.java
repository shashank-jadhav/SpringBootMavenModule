package com.shashank.phhub.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.shashank.phhub.auth.custom.filter.CustomConcurrentSessionFilter;
import com.shashank.phhub.auth.custom.filter.JsonAuthenticationFailureHandler;
import com.shashank.phhub.auth.custom.filter.JsonAuthenticationSuccessHandler;
import com.shashank.phhub.auth.custom.filter.JsonUsernamePasswordAuthenticationFilter;
import com.shashank.phhub.service.security.SecurityUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JsonAuthenticationSuccessHandler jsonAuthenticationSuccessHandler;

	@Autowired
	private JsonAuthenticationFailureHandler jsonAuthenticationFailureHandler;

	@Autowired
	private SessionRegistry sessionRegistry;

	@Autowired
	private CustomConcurrentSessionFilter customConcurrentSessionFilter;

	@Autowired
	private CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/web/**", "/login", "/swagger-ui.html").permitAll().and()
				.authorizeRequests().antMatchers("/api/ph/a/**").authenticated().and().sessionManagement()
				.sessionAuthenticationStrategy(compositeSessionAuthenticationStrategy);
		http.addFilter(customConcurrentSessionFilter);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(securityUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JsonUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
		JsonUsernamePasswordAuthenticationFilter authFilter = new JsonUsernamePasswordAuthenticationFilter();
		authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		authFilter.setAuthenticationManager(authenticationManagerBean());
		authFilter.setAuthenticationSuccessHandler(jsonAuthenticationSuccessHandler);
		authFilter.setAuthenticationFailureHandler(jsonAuthenticationFailureHandler);
		authFilter.setSessionAuthenticationStrategy(compositeSessionAuthenticationStrategy);
		return authFilter;
	}

	@Bean
	public SessionRegistry sessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean
	public CustomConcurrentSessionFilter customConcurrentSessionFilter() {
		CustomConcurrentSessionFilter customConcurrentSessionFilter = new CustomConcurrentSessionFilter(
				sessionRegistry);
		return customConcurrentSessionFilter;
	}

	@Bean
	public CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy() {
		List<SessionAuthenticationStrategy> delegateStrategies = new ArrayList<>();

		ConcurrentSessionControlAuthenticationStrategy concurrentSessionControlAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(
				sessionRegistry);
		concurrentSessionControlAuthenticationStrategy.setMaximumSessions(1);
		concurrentSessionControlAuthenticationStrategy.setExceptionIfMaximumExceeded(false);

		delegateStrategies.add(concurrentSessionControlAuthenticationStrategy);
		delegateStrategies.add(new SessionFixationProtectionStrategy());
		delegateStrategies.add(new RegisterSessionAuthenticationStrategy(sessionRegistry));

		CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy = new CompositeSessionAuthenticationStrategy(
				delegateStrategies);
		return compositeSessionAuthenticationStrategy;
	}

}