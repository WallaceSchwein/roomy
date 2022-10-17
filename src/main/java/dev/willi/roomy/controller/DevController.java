package dev.willi.roomy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevController {

	@GetMapping
	public String home() {
		return "Hello World!";
	}
	
	@GetMapping("/user")
	public String user() {
		return "Hello User!";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public String admin() {
		return "Hello Admin!";
	}
	
}
