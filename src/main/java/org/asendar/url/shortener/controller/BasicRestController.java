/**
 * 
 */
package org.asendar.url.shortener.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

/**
 * @author asendar
 *
 */
@RestController
public class BasicRestController {

	/**
	 * 
	 * @return
	 */
	protected ResponseEntity<Map<String, Object>> successResponse() {
		return new ResponseEntity<Map<String, Object>>(buildResponseWithParams("success", true), HttpStatus.OK);
	}

	/**
	 * 
	 * @param objects
	 * @return
	 */
	protected ResponseEntity<Map<String, Object>> successResponseWithParams(Object... objects) {
		return new ResponseEntity<Map<String, Object>>(buildResponseWithParams(objects), HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	protected ResponseEntity<Map<String, Object>> errorResponse() {
		return new ResponseEntity<Map<String, Object>>(buildResponseWithParams("success", false),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param objects
	 * @return
	 */
	protected ResponseEntity<Map<String, Object>> errorResponseWithParams(Object... objects) {
		return new ResponseEntity<Map<String, Object>>(buildResponseWithParams(objects), HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param objects
	 * @return
	 */
	private Map<String, Object> buildResponseWithParams(Object... objects) {
		if (objects.length % 2 != 0) {
			return null;
		}
		for (int i = 0; i < objects.length; i = i + 2) {
			if (!(objects[i] instanceof String)) {
				return null;
			}
		}
		Map<String, Object> map = Maps.newHashMap();
		for (int i = 0; i < objects.length; i = i + 2) {
			map.put((String) objects[i], objects[i + 1]);
		}
		return map;
	}

}
