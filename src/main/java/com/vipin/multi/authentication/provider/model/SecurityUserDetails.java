/**
 * 
 */
package com.vipin.multi.authentication.provider.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vipin.multi.authentication.provider.entity.Authority;
import com.vipin.multi.authentication.provider.entity.User;

/**
 * @author a13400520
 *
 */
public class SecurityUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final User user;

	public SecurityUserDetails(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> authoritySet=this.user.getAuthority();
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
		for (Authority authority : authoritySet) {
			grantedAuthorityList.add(()->authority.getAUTHORITY());
		}
		return grantedAuthorityList;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isAccountNonLocked() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return Boolean.TRUE;
	}

	@Override
	public boolean isEnabled() {
		return this.user.isEnabled();
	}

	public User getUser() {
		return user;
	}
	
	

}
