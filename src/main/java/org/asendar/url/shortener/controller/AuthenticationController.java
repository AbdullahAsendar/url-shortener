package org.asendar.url.shortener.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class AuthenticationController extends BasicRestController{
	
	@ApiOperation("Login.")
	@PostMapping("/login")
	public void mockLogin(@ApiParam("Email") @RequestParam String username, @ApiParam("Password") @RequestParam String password) {
		throw new IllegalStateException(
				"This method shouldn't be called. It's implemented by Spring Security filters.");
	}
}
