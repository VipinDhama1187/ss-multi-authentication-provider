/**
 * 
 */
package com.vipin.multi.authentication.provider.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vipin.multi.authentication.provider.entity.Otp;
import com.vipin.multi.authentication.provider.entity.User;
import com.vipin.multi.authentication.provider.repository.OtpRepository;
import com.vipin.multi.authentication.provider.repository.UserRepository;
import com.vipin.multi.authentication.provider.service.OtpService;

/**
 * @author a13400520
 *
 */
@Service("otpService")
public class OtpServiceImpl implements OtpService {

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Otp loadUserByUsername(String username) {

		User user = findUserByUsername(username);

		 Optional<Otp> otpUser = otpRepository.findByUser(user);
		 if(otpUser.isPresent())
			 return otpUser.get();
		 else 
			 return null;
	}

	private User findUserByUsername(String username) {
		var optional = userRepository.findUserByUsername(username);

		User user = optional.orElseThrow(
				() -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
		return user;
	}
	
	@Override
	public void saveOtp(Otp otp) {
		otpRepository.save(otp);
	}


}
