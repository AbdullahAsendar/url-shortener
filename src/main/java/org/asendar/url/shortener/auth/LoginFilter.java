/**
 * 
 */
package org.asendar.url.shortener.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.asendar.url.shortener.service.user.LoginModel;
import org.asendar.url.shortener.service.user.UserModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author asendar
 *
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	public LoginFilter(String urlMapping, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(urlMapping));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		final LoginModel user;
		if (!CollectionUtils.isEmpty(request.getParameterMap())) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			user = new LoginModel(username, password);
		} else {
			user = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
		}

		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword());
		return getAuthenticationManager().authenticate(loginToken);
	}

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest httpRequest = (HttpServletRequest) request;

		// extract token from header
		final String accessToken = httpRequest.getHeader("header-name");
		if (null != accessToken) {
			// get and check whether token is valid ( from DB or file wherever you are
			// storing the token)

			// Populate SecurityContextHolder by fetching relevant information using token
			final UserModel user = new UserModel();
			user.setEmail(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, null);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		}

		chain.doFilter(request, response);
	}

}