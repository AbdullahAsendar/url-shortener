/**
 * 
 */
package org.asendar.url.shortener.service.user;

import javax.annotation.Nonnull;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

/**
 * @author asendar
 *
 */
@Setter
@Getter
public class UserModel {

	@Nonnull
	@ApiParam(name = "username", value = "The username", required = true)
	private String username;

	@Nonnull
	@ApiParam(name = "email", value = "Email address", required = true)
	private String email;

	@Nonnull
	@ApiParam(name = "firstName", value = "First Name", required = true)
	private String firstName;

	@Nonnull
	@ApiParam(name = "lastName", value = "Last Name", required = true)
	private String lastName;

	@Nonnull
	@ApiParam(name = "password", value = "Password", required = true)
	private String password;

}
