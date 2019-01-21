/**
 * 
 */
package org.asendar.url.shortener.controller;

import java.util.Map;

import javax.validation.Valid;

import org.asendar.url.shortener.entity.UserEntity;
import org.asendar.url.shortener.entity.UserRole;
import org.asendar.url.shortener.exception.UrlShortenerException;
import org.asendar.url.shortener.model.user.UserService;
import org.asendar.url.shortener.service.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;

/**
 * @author asendar
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends BasicRestController{

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	@ApiImplicitParam(name = TOKEN_HEADER, value = "Create auth token", required = true, dataType = STRING, paramType = HEADER)
	public ResponseEntity<Map<String, Object>> createUser(@Valid @ModelAttribute UserModel userModel) {
		UserEntity userEntity = new UserEntity();

		userEntity.setFirstName(userModel.getFirstName());
		userEntity.setLastName(userModel.getLastName());

		userEntity.setUsername(userModel.getUsername());
		userEntity.setPassword(userModel.getPassword());
		
		userEntity.setEmail(userModel.getEmail());
		
		userEntity.grantRole(UserRole.USER);

		return successResponseWithParams("User", userService.saveOrUpdate(userEntity).map(UserEntity::toString).orElseThrow(() -> new UrlShortenerException("Error creating the user")));
	}

}
