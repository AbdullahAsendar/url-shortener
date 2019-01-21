package org.asendar.url.shortener.service.security;

import org.asendar.url.shortener.entity.UserEntity;
import org.asendar.url.shortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author asendar
 *
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	@Override
	public final UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
		final UserEntity user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("user not found"));
		detailsChecker.check(user);
		return user;
	}
}