package dev.willi.roomy.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.willi.roomy.model.entity.Booking;
import dev.willi.roomy.model.entity.User;
import dev.willi.roomy.service.BookingService;

@RestController
@RequestMapping(path = "api/v1/booking")
public class BookingController {
	
	// attributes
	private final BookingService bookingService;

	// constructors	
	
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	// end points
	
	@GetMapping("/{email}")
	public List<Booking> getBookingsByUser(@PathVariable String email) {
		return bookingService.getBookingsByUser(email);
	}
	
	@PostMapping
	public String createBooking(@RequestBody Booking booking) {
		return bookingService.createBooking(booking);
	}
	
	@PutMapping
	public String updateBooking(@RequestBody Booking booking) {
		return bookingService.updateBooking(booking);
	}
	
	@DeleteMapping("/{user}/{date}")
	public String deleteBooking(@PathVariable User user, 
								@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
		return bookingService.deleteBooking(user, date);
	}

}
