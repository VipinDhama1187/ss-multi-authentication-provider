package com.vipin.multi.authentication.provider.security.filter;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vipin.multi.authentication.provider.entity.Otp;
import com.vipin.multi.authentication.provider.model.SecurityUserDetails;
import com.vipin.multi.authentication.provider.model.TokenManager;
import com.vipin.multi.authentication.provider.security.authentications.UsernameOtpAuthentication;
import com.vipin.multi.authentication.provider.security.authentications.UsernamePasswordAuthentication;
import com.vipin.multi.authentication.provider.service.OtpService;

//@Component
public class UsernameAuthenticationFilter extends OncePerRequestFilter {

	// @Autowired
	private AuthenticationManager manager;

	// @Autowired
	private OtpService otpService;

	// @Autowired
	private UserDetailsService usernamePasswordService;

	private TokenManager tokenManager;

	public UsernameAuthenticationFilter(AuthenticationManager manager, OtpService otpService,
			UserDetailsService usernamePasswordService, TokenManager tokenManager) {
		super();
		this.manager = manager;
		this.otpService = otpService;
		this.usernamePasswordService = usernamePasswordService;
		this.tokenManager = tokenManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		/**
		 * we have 2 cases 1. when username and password entered. authenticate and
		 * create Otp, insert into OTP table 2. when username and otp entered
		 * authenticate and return UUID in response.
		 */

		var username = request.getHeader("username");
		var password = request.getHeader("password");
		var otp = request.getHeader("otp");
		try {
			if (null == otp) {
				Authentication authentication = new UsernamePasswordAuthentication(username, password);
				authentication = manager.authenticate(authentication);
				if (authentication.isAuthenticated()) {
					Otp otpEntity = otpService.loadUserByUsername(username);
					Integer code = new Random().nextInt(9999) + 1000;
					if (null != otpEntity) {
						otpEntity.setOtp(code);
					} else {
						SecurityUserDetails userDetails = (SecurityUserDetails) usernamePasswordService
								.loadUserByUsername(username);
						otpEntity = new Otp();
						otpEntity.setOtp(code);
						otpEntity.setUser(userDetails.getUser());
					}
					otpService.saveOtp(otpEntity);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					response.setHeader("otp", code + "");
				} else
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			} else {
				Authentication authentication = new UsernameOtpAuthentication(username, otp, null);
				authentication = manager.authenticate(authentication);
				if (authentication.isAuthenticated()) {
					String auuid = UUID.randomUUID().toString();
					this.tokenManager.addToken(auuid);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					response.setHeader("Authorization", auuid);
				}
			}
		} catch (AuthenticationException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !request.getServletPath().equals("/login");
	}

}
