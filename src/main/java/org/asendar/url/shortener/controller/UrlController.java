/**
 * 
 */
package org.asendar.url.shortener.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.asendar.url.shortener.entity.ShortenedUrlEntity;
import org.asendar.url.shortener.entity.UserAuthentication;
import org.asendar.url.shortener.entity.UserEntity;
import org.asendar.url.shortener.exception.UrlShortenerException;
import org.asendar.url.shortener.service.common.CommonUtils;
import org.asendar.url.shortener.service.url.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;

/**
 * @author asendar
 *
 */
@RestController
@RequestMapping("/url")
public class UrlController extends BasicRestController{

	@Autowired
	private UrlService urlService;

	@Autowired
	private CommonUtils commonUtils;

	@GetMapping("/shorten")
	@ApiImplicitParam(name = TOKEN_HEADER, value = "Create auth token", required = true, dataType = STRING, paramType = HEADER)
	public ResponseEntity<Map<String, Object>> shorten(HttpServletRequest request, UserAuthentication authentication, @RequestParam String originalUrl) {
		UserEntity userEntity = authentication.getDetails();
		return successResponseWithParams("Shortened URL:", commonUtils.getBaseUrl(request) + "url/"
				+ urlService.shortenUrl(originalUrl, userEntity).map(ShortenedUrlEntity::getShortened)
				.orElseThrow(() -> new UrlShortenerException("Error while shortening the URL")));
	}

	@GetMapping("/{shortenedUrl}/get")
	public ResponseEntity<Map<String, Object>> original(HttpServletResponse response, HttpServletRequest request, @PathVariable String shortenedUrl) throws IOException {
		response.sendRedirect(
				urlService.getOriginalUrl(shortenedUrl, 
						commonUtils.
						getClientIp(request)).
						map(ShortenedUrlEntity::getOriginal).
						orElseThrow(() -> new UrlShortenerException("URL [%s] was not found!",shortenedUrl)));
		
		return successResponse();
	}

}
