package com.vipin.multi.authentication.provider.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.vipin.multi.authentication.provider.model.TokenManager;
import com.vipin.multi.authentication.provider.security.authentications.UsernameAuthorizationAuthentication;

@Component
public class UsernameAuthorizationAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private TokenManager tokenManager;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String authorization = (String) authentication.getCredentials();
		if (tokenManager.isTokenExists(authorization))
			return new UsernameAuthorizationAuthentication(authorization, null, null);
		throw new BadCredentialsException("Bad Authorization!!");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernameAuthorizationAuthentication.class.equals(authentication);
	}

}
