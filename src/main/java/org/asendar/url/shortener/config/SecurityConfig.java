/**
 * 
 */
package org.asendar.url.shortener.config;

import org.asendar.url.shortener.auth.AuthenticationProvider;
import org.asendar.url.shortener.auth.StatelessAuthenticationFilter;
import org.asendar.url.shortener.auth.StatelessLoginFilter;
import org.asendar.url.shortener.service.security.TokenAuthenticationService;
import org.asendar.url.shortener.service.security.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author asendar
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	public SecurityConfig() {
		super(true);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.exceptionHandling()
				.and()
				.anonymous()
				.and()
				.servletApi()
				.and()
				.headers()
				.and()
				.authorizeRequests()
								
				//allow anonymous resource requests
				.antMatchers("/").permitAll()
				.antMatchers("/favicon.ico").permitAll()
				.antMatchers("/resources/**").permitAll()
				
				//allow anonymous POSTs to login
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				
				//allow anonymous GETs to API
				.antMatchers(HttpMethod.GET, "/api/**").permitAll()
				.antMatchers(HttpMethod.GET, "/url/*/get").permitAll()
				
				//defined Admin only API area
				.antMatchers("/admin/**").hasRole("ADMIN")
				
				//all other request need to be authenticated
				.anyRequest().hasRole("USER").and()				
		
				// custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
				.addFilterBefore(new StatelessLoginFilter("/login", tokenAuthenticationService, userDetailsService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)

				// custom Token based authentication based on the header previously given to the client
				.addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider).userDetailsService(userDetailsService).passwordEncoder(encoder());

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");

	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
