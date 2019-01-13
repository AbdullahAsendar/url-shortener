/**
 * 
 */
package org.asendar.url.shortener.auth;

import java.util.ArrayList;

import org.asendar.url.shortener.entity.UserEntity;
import org.asendar.url.shortener.exception.UrlShortenerException;
import org.asendar.url.shortener.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author asendar
 *
 */
@Component
public class PasswordAuthenticationProvider  implements AuthenticationProvider {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
 
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
  
        String email = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserEntity userEntity = userService.findOne(email)
				.orElseThrow(() -> new UrlShortenerException("User with email %s was not found", email));
		
		
		
		if(!passwordEncoder.matches(password, userEntity.getPassword()))
			new UrlShortenerException("Authentication error");

		return new UsernamePasswordAuthenticationToken(userEntity.getUsername(), password, new ArrayList<>());

	}
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
          UsernamePasswordAuthenticationToken.class);
    }
}