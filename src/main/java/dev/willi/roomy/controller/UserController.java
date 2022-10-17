package dev.willi.roomy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.willi.roomy.model.entity.User;
import dev.willi.roomy.service.UserService;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

	// attributes
	
	private final UserService userService;

	// constructors
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// end points

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping
	public String updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{email}")
	public String deleteUser(@PathVariable String email) {
		return userService.deleteUserByMail(email);
	}
}
