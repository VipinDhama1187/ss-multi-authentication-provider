package com.vipin.multi.authentication.provider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vipin.multi.authentication.provider.entity.Otp;
import com.vipin.multi.authentication.provider.entity.User;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
	
	public Optional<Otp> findByUser(User user);
	
}
