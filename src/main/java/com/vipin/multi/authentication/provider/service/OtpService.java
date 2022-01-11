package com.vipin.multi.authentication.provider.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vipin.multi.authentication.provider.entity.Otp;

public interface OtpService {

	Otp loadUserByUsername(String username) throws UsernameNotFoundException;

	void saveOtp(Otp otp);

}
