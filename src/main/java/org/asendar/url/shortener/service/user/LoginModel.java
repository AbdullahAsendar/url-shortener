/**
 * 
 */
package org.asendar.url.shortener.service.user;

import javax.annotation.Nonnull;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author asendar
 *
 */
@Setter
@Getter
@AllArgsConstructor
public class LoginModel {

	@Nonnull
	@ApiParam(name = "username", value = "Email address", required = true)
	private String username;

	@Nonnull
	@ApiParam(name = "password", value = "Password", required = true)
	private String password;

}
