package org.asendar.url.shortener.service.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.asendar.url.shortener.entity.UserAuthentication;
import org.asendar.url.shortener.entity.UserEntity;
import org.asendar.url.shortener.exception.UrlShortenerException;
import org.asendar.url.shortener.model.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author asendar
 *
 */
@Service
public class TokenAuthenticationService {
	private static final String AUTH_HEADER_NAME = "AUTH-TOKEN";

	private final TokenHandler tokenHandler;
	
	@Autowired
	private UserService userService;

	@Autowired
	public TokenAuthenticationService(@Value("${token.secret}") String secret) {
		tokenHandler = new TokenHandler(DatatypeConverter.parseBase64Binary(secret));
	}

	public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) throws IOException {
		final UserEntity user = authentication.getDetails();
		
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(
	            "{\"" + AUTH_HEADER_NAME+ "\":\"" + tokenHandler.createTokenForUser(user) + "\"}"
	    );
		
	}

	public Authentication getAuthentication(HttpServletRequest request) {
		final String token = request.getHeader(AUTH_HEADER_NAME);
		if (token != null) {
			final UserEntity user = tokenHandler.parseUserFromToken(token);
			if (user != null) {
				return new UserAuthentication(userService.findOne(user.getId())
						.orElseThrow(() -> new UrlShortenerException("User with id [%s] was not found", user.getId())));
			}
		}
		return null;
	}
}
