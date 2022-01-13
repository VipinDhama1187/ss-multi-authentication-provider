package com.vipin.multi.authentication.provider.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class TokenManager {
	
	private Set<String> authorizationSet = new HashSet<>();

	public Set<String> getAuthorizationSet() {
		return authorizationSet;
	}

	public void setAuthorizationSet(Set<String> authorizationSet) {
		this.authorizationSet = authorizationSet;
	}

	public void addToken(String token) {
		this.authorizationSet.add(token);
	}

	public boolean isTokenExists(String token) {
		return this.authorizationSet.contains(token);
	}

}
