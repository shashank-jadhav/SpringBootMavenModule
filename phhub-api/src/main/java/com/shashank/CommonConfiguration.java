package com.shashank;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shashank.phhub.auth.custom.filter.JsonAuthenticationFailureHandler;
import com.shashank.phhub.auth.custom.filter.JsonAuthenticationSuccessHandler;

@Configuration
public class CommonConfiguration {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:locale/appErrorMessages_en_GB",
				"classpath:locale/appSuccessMessages_en_GB");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(0);
		return messageSource;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public JsonAuthenticationSuccessHandler successHandler() {
		JsonAuthenticationSuccessHandler successHandler = new JsonAuthenticationSuccessHandler();
		return successHandler;
	}

	@Bean
	public JsonAuthenticationFailureHandler failureHandler() {
		JsonAuthenticationFailureHandler failureHandler = new JsonAuthenticationFailureHandler();
		return failureHandler;
	}
	
	

}
