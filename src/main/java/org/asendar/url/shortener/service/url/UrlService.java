/**
 * 
 */
package org.asendar.url.shortener.service.url;

import java.util.Optional;

import org.asendar.url.shortener.entity.ShortenedUrlEntity;
import org.asendar.url.shortener.entity.UserEntity;

/**
 * @author asendar
 *
 */
public interface UrlService {

	Optional<ShortenedUrlEntity> saveOrUpdate(ShortenedUrlEntity shortenedUrlEntity);
	
	Optional<ShortenedUrlEntity>  shortenUrl(String originalUrl, UserEntity user);

	Optional<ShortenedUrlEntity> findOne(long id);
	
	Optional<ShortenedUrlEntity> findOne(String shortenedUrl);
	
	Optional<ShortenedUrlEntity> getOriginalUrl(String shortenedUrl, String ipAddress);
	
	int getNumberOfVisits(String shortenedUrl);

}
