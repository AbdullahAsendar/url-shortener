/**
 * 
 */
package org.asendar.url.shortener.controller;

import javax.validation.Valid;

import org.asendar.url.shortener.entity.UserEntity;
import org.asendar.url.shortener.exception.UrlShortenerException;
import org.asendar.url.shortener.model.user.UserService;
import org.asendar.url.shortener.service.user.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public String createUser(@Valid  @ModelAttribute UserModel userModel) {
		UserEntity userEntity = new UserEntity();

		userEntity.setFirstName(userModel.getFirstName());
		userEntity.setLastName(userModel.getLastName());

		userEntity.setUsername(userModel.getUsername());
		userEntity.setPassword(userModel.getPassword());

		return userService.saveOrUpdate(userEntity).map(UserEntity::toString).orElseThrow(() -> new UrlShortenerException("Error creating the user"));
	}

}
