/**
 * 
 */
package org.asendar.url.shortener.service.common;

import java.security.SecureRandom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

/**
 * @author asendar
 *
 */
@Service
public class CommonUtils {

	private volatile SecureRandom numberGenerator = new SecureRandom();
	private volatile short sequence = (short) numberGenerator.nextInt();

	public synchronized long newID() {
		return (System.currentTimeMillis() << 20) | ((sequence++ & 0xFFFF) << 4) | (numberGenerator.nextInt() & 0xF);
	}

	public synchronized String newUrl() {
		return String.valueOf(newID());
	}

	public String getBaseUrl(HttpServletRequest request) {
		return String.format("%s://%s:%d/", request.getScheme(), request.getServerName(), request.getServerPort());
	}

	public String getClientIp(HttpServletRequest request) {

		String remoteAddr = "unknown-ip";

		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}

		return remoteAddr;
	}

}
