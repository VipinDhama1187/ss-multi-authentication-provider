package com.vipin.multi.authentication.provider.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.vipin.multi.authentication.provider.security.filter.UsernameAuthenticationFilter;
import com.vipin.multi.authentication.provider.service.OtpService;

@SuppressWarnings("deprecation")
@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider usernamePasswordAuthenticationProvider;

	@Autowired
	private AuthenticationProvider usernameOtpAuthenticationProvider;

	//@Autowired
	//private UsernameAuthenticationFilter usernameAuthenticationFilter;
	
	@Autowired
	private OtpService otpService;
	
	@Autowired
	private UserDetailsService usernamePasswordService;

	/**
	 * creating AuthenticationManager for sending the Authentication object from
	 * filter to AuthenticationProvider
	 */
	@Override
	@Bean("manager")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean("usernameAuthenticationFilter")
	public UsernameAuthenticationFilter userAuthenticationFilter() throws Exception {
		return new UsernameAuthenticationFilter(authenticationManagerBean(), otpService, usernamePasswordService);
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(usernamePasswordAuthenticationProvider)
				.authenticationProvider(usernameOtpAuthenticationProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterAt(userAuthenticationFilter(), BasicAuthenticationFilter.class);
	}

}
