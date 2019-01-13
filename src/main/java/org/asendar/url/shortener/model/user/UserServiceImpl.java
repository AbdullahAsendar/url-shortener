/**
 * 
 */
package org.asendar.url.shortener.model.user;

import java.util.Optional;

import org.asendar.url.shortener.entity.UserEntity;
import org.asendar.url.shortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author asendar
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Optional<UserEntity> saveOrUpdate(UserEntity userEntity) {
		userEntity.setPassword(encodePassword(userEntity.getPassword()));
		return Optional.ofNullable(userRepository.saveAndFlush(userEntity));
	}

	@Override
	public Optional<UserEntity> findOne(long id) {
		return userRepository.findById(id);
	}

	private String encodePassword(String passwordText) {
		return passwordEncoder.encode(passwordText);
	}

	@Override
	public Optional<UserEntity> findOne(String email) {
		return userRepository.findByEmail(email);
	}

}
