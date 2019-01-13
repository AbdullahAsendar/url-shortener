/**
 * 
 */
package org.asendar.url.shortener.exception;

/**
 * @author asendar
 *
 */
public class UrlShortenerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 26473066403476416L;

	public UrlShortenerException(String reason) {
		super(String.format(reason));
	}

	public UrlShortenerException(String format, Object... args) {
		super(String.format(format, args));
	}

}
