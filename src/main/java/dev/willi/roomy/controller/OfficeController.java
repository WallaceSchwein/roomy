package dev.willi.roomy.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.willi.roomy.model.entity.Office;
import dev.willi.roomy.service.OfficeService;

@RestController
@RequestMapping(path = "api/v1/office")
public class OfficeController {
	
	// attributes
	
	private final OfficeService officeService;
	
	// constructors
	
	public OfficeController(OfficeService officeService) {
		this.officeService = officeService;
	}
	
	// end points
	
	@GetMapping("/{adress}/{date}")
	public List<String> getOfficeWorkload(@PathVariable String adress, 
										  @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		return officeService.getOfficeWorkload(adress, date);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public String createOffice(@RequestBody Office office) {
		return officeService.createOffice(office);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping
	public String updateOffice(@RequestBody Office office) {
		return officeService.updateOffice(office);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{adress}")
	public String deleteOfficeByAdress(@PathVariable String adress) {
		return officeService.deleteOfficeByAdress(adress);
	}
}
