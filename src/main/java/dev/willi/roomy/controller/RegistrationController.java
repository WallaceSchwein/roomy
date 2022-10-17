package dev.willi.roomy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.willi.roomy.model.RegistrationRequest;
import dev.willi.roomy.service.RegistrationService;

@RestController
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {
	
	//attributes

	private final RegistrationService registrationService;
	
	//constructors

	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	// end points

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public String register(@RequestBody RegistrationRequest request) {
		return registrationService.register(request);
	}

	@GetMapping(path = "confirm")
	public String confirm(@RequestParam("token") String token) {
		return registrationService.confirmToken(token);
	}
	
}
