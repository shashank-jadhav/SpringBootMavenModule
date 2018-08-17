package com.shashank.phhub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/web/**", "/api/ph/p/**" , "/login" , "/swagger-ui.html").permitAll().and()
				.authorizeRequests().antMatchers("/api/ph/a/**").hasAnyRole("NORMAL_USER", "ADMIN_USER");
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
		return authFilter;
	}

}