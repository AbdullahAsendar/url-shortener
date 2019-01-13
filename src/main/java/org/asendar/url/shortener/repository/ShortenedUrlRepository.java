/**
 * 
 */
package org.asendar.url.shortener.repository;

import java.util.Optional;

import org.asendar.url.shortener.entity.ShortenedUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author asendar
 *
 */
@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrlEntity, Long> {

	Optional<ShortenedUrlEntity> findByShortened(String shortenedUrl);
}
