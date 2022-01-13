package com.vipin.multi.authentication.provider.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vipin.multi.authentication.provider.security.authentications.UsernameAuthorizationAuthentication;

public class UserAuthorizationAuthenticationFilter extends OncePerRequestFilter {

	private AuthenticationManager manager;

	public UserAuthorizationAuthenticationFilter(AuthenticationManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");
		Authentication authentication = new UsernameAuthorizationAuthentication(null, authorization);
		try {
			Authentication result = this.manager.authenticate(authentication);
			if (result.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(result);
				filterChain.doFilter(request, response);
			}
		} catch (AuthenticationException ex) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getServletContext().equals("/login");
	}

}
