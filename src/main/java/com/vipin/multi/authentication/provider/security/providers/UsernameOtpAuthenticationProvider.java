package com.vipin.multi.authentication.provider.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.vipin.multi.authentication.provider.entity.Otp;
import com.vipin.multi.authentication.provider.model.SecurityUserDetails;
import com.vipin.multi.authentication.provider.security.authentications.UsernameOtpAuthentication;
import com.vipin.multi.authentication.provider.service.OtpService;


@Component
public class UsernameOtpAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private OtpService otpService;
	
	@Autowired
	private UserDetailsService usernamePasswordService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		var username = authentication.getName();
		var otp = (String)authentication.getCredentials();
		Otp otpEntity = otpService.loadUserByUsername(username);
		if(null!=otpEntity) {
			if(otp.equals(String.valueOf(otpEntity.getOtp()))) {
				SecurityUserDetails userdetails = (SecurityUserDetails) usernamePasswordService.loadUserByUsername(username);
				return new UsernameOtpAuthentication(username, otp, userdetails.getAuthorities());
			}else
				throw new BadCredentialsException("Bad credentials!");
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernameOtpAuthentication.class.equals(authentication);
	}

}
