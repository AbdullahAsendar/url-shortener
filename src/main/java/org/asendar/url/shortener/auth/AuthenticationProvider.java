package org.asendar.url.shortener.auth;

import org.asendar.url.shortener.entity.UserAuthentication;
import org.asendar.url.shortener.entity.UserEntity;
import org.asendar.url.shortener.service.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String email = authentication.getName();
		byte[] password = authentication.getCredentials().toString().getBytes();

		UserEntity user = null;
		try {
			user = userDetailsService.loadUserByUsername(email);
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException("Failed to authenticate using given paramters", e);
		}

		if (!user.isEnabled()) {
			throw new BadCredentialsException("User is not active");
		}

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
		boolean matches = passwordEncoder.matches(new String(password), user.getPassword());
		if (!matches) {
			throw new AuthenticationCredentialsNotFoundException("Failed to authenticate using given paramters");
		}

		return new UserAuthentication(user);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}