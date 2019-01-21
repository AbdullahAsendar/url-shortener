package org.asendar.url.shortener.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * @author asendar
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {



	/**
	 * Handle form's validation errors
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> processValidationError(HttpServletRequest request,
			MethodArgumentNotValidException ex) {
//		BindingResult result = ex.getBindingResult();
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString()),
				HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handle not authenticated user exception
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleNotAuthenticatedUserException(HttpServletRequest request, AuthenticationException ex) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString()),
				HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Handle access denied exceptions
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException ex) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN.toString()),
				HttpStatus.FORBIDDEN);
	}

	/**
	 * Handle internal server exception
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<ErrorResponse> handleDataAccessException(HttpServletRequest request, DataAccessException ex) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle generic exception
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Throwable ex) {
		return new ResponseEntity<ErrorResponse>(
				new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Setter
	@Getter
	@AllArgsConstructor
	private class ErrorResponse {
		private String message;
		private String status;
	}
}
