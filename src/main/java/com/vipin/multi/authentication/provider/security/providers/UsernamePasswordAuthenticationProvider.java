package com.vipin.multi.authentication.provider.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vipin.multi.authentication.provider.security.authentications.UsernamePasswordAuthentication;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService usernamePasswordService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var username = authentication.getName();
		var password = (String)authentication.getCredentials();
		UserDetails loadUserByUsername = usernamePasswordService.loadUserByUsername(username);
		if(null!=loadUserByUsername) {
			if(passwordEncoder.matches(password, loadUserByUsername.getPassword())) {
				return new UsernamePasswordAuthentication(username, password, loadUserByUsername.getAuthorities());
			}else
				throw new BadCredentialsException("Bad credentials!");
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthentication.class.equals(authentication);
	}

}
