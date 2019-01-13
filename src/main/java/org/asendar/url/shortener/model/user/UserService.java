/**
 * 
 */
package org.asendar.url.shortener.model.user;

import java.util.Optional;

import org.asendar.url.shortener.entity.UserEntity;

/**
 * @author asendar
 *
 */
public interface UserService {

	Optional<UserEntity> saveOrUpdate(UserEntity userEntity);

	Optional<UserEntity> findOne(long id);
	
	Optional<UserEntity> findOne(String email);

}
