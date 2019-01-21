package org.asendar.url.shortener.controller;

import org.asendar.url.shortener.service.user.LoginModel;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class AuthenticationController extends BasicRestController{
	
	@ApiOperation(value = "Login")
	@PostMapping(value = "/login")
	public void mockLogin(@ModelAttribute @RequestBody LoginModel loginModel) {
		throw new IllegalStateException(
				"This method shouldn't be called. It's implemented by Spring Security filters.");
	}
}
