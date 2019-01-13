/**
 * 
 */
package org.asendar.url.shortener.service.url;

import java.util.List;
import java.util.Optional;

import org.asendar.url.shortener.entity.ShortenedUrlEntity;
import org.asendar.url.shortener.entity.UrlAccessEntity;
import org.asendar.url.shortener.entity.UserEntity;
import org.asendar.url.shortener.repository.ShortenedUrlRepository;
import org.asendar.url.shortener.service.common.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * @author asendar
 *
 */
@Service
public class UrlServiceImpl implements UrlService {
	
	@Autowired
	private ShortenedUrlRepository shortenedUrlRepo;

	@Autowired
	private CommonUtils commonUtils;
	

	@Override
	public Optional<ShortenedUrlEntity> saveOrUpdate(ShortenedUrlEntity shortenedUrlEntity) {
		return Optional.ofNullable(shortenedUrlRepo.saveAndFlush(shortenedUrlEntity));
	}

	@Override
	public Optional<ShortenedUrlEntity>  shortenUrl(String originalUrl, UserEntity user) {
		ShortenedUrlEntity shortenedUrlEntity = new ShortenedUrlEntity();
		shortenedUrlEntity.setUser(user);
		shortenedUrlEntity.setOriginal(originalUrl);
		shortenedUrlEntity.setShortened(commonUtils.newUrl());

		return Optional.ofNullable(shortenedUrlRepo.saveAndFlush(shortenedUrlEntity));
	}

	@Override
	public Optional<ShortenedUrlEntity> findOne(long id) {
		return shortenedUrlRepo.findById(id);
	}
	

	@Override
	public Optional<ShortenedUrlEntity> findOne(String shortenedUrl) {
		return shortenedUrlRepo.findByShortened(shortenedUrl);
	}

	@Override
	public Optional<ShortenedUrlEntity> getOriginalUrl(String shortenedUrl, String ipAddress) {

		ShortenedUrlEntity entity = findOne(shortenedUrl).orElse(null);

		if (entity != null) {
			UrlAccessEntity urlAccessEntity = new UrlAccessEntity();
			urlAccessEntity.setIpAddress(ipAddress);
			urlAccessEntity.setShortenedUrl(entity);
			entity.addAccess(urlAccessEntity);
			
			saveOrUpdate(entity);
		}

		return Optional.ofNullable(entity);
	}

	@Override
	public int getNumberOfVisits(String shortenedUrl) {

		List<UrlAccessEntity> access = findOne(shortenedUrl).map(ShortenedUrlEntity::getAccess)
				.orElse(Lists.newArrayList());

		return access.size();
	}


}
