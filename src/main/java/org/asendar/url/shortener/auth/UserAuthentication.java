package org.asendar.url.shortener.auth;

import java.util.Collection;

import org.asendar.url.shortener.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.google.common.collect.Lists;

/**
 * @author asendar
 *
 */
public class UserAuthentication implements Authentication {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4027932697208640328L;
	private UserEntity user;
	private boolean authenticated = true;

	public UserAuthentication(UserEntity user) {
		this.user = user;
	}

	@Override
	public String getName() {
		return String.format("%s %s", user.getFirstName(), user.getLastName());
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return Lists.newArrayList();
	}

	@Override
	public Object getCredentials() {
		return user.getPassword();
	}

	@Override
	public UserEntity getDetails() {
		return this.user;
	}

	@Override
	public String getPrincipal() {
		return this.user.getUsername();
	}

	@Override
	public boolean isAuthenticated() {
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.authenticated = isAuthenticated;
	}
}