package com.vipin.multi.authentication.provider.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vipin.multi.authentication.provider.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findUserByUsername(String username);
}
