/**
 * 
 */
package org.asendar.url.shortener.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.asendar.url.shortener.entity.ShortenedUrlEntity;
import org.asendar.url.shortener.entity.UserEntity;
import org.asendar.url.shortener.exception.UrlShortenerException;
import org.asendar.url.shortener.model.user.UserService;
import org.asendar.url.shortener.service.common.CommonUtils;
import org.asendar.url.shortener.service.url.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asendar
 *
 */
@RestController
@RequestMapping("/url")
public class UrlController {

	@Autowired
	private UrlService urlService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private CommonUtils commonUtils;

	@GetMapping("/shorten")
	public String shorten(HttpServletRequest request, @RequestParam String originalUrl, @RequestParam long userId) {
		UserEntity userEntity = userService.findOne(userId).orElseThrow(() -> new UrlShortenerException("User with id [%s] was not found!", userId));
		return commonUtils.getBaseUrl(request) + "url/"
				+ urlService.shortenUrl(originalUrl, userEntity).map(ShortenedUrlEntity::getShortened)
						.orElseThrow(() -> new UrlShortenerException("Error while shortening the URL"));
	}

	@GetMapping("/{shortenedUrl}")
	public void original(HttpServletResponse response, HttpServletRequest request, @PathVariable String shortenedUrl) throws IOException {
		response.sendRedirect(
				urlService.getOriginalUrl(shortenedUrl, 
						commonUtils.
						getClientIp(request)).
						map(ShortenedUrlEntity::getOriginal).
						orElseThrow(() -> new UrlShortenerException("URL [%s] was not found!",shortenedUrl)));
	}

}
