/**
 * 
 */
package org.asendar.url.shortener.repository;

import java.util.Optional;

import org.asendar.url.shortener.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author asendar
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByEmail(String email);

}
