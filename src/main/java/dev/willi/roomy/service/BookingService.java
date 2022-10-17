package dev.willi.roomy.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import dev.willi.roomy.model.entity.Booking;
import dev.willi.roomy.model.entity.User;
import dev.willi.roomy.repository.BookingRepository;

@Service
public class BookingService {
	

	// attributes
	
	private final BookingRepository bookingRepository;
	private final UserService userService;
	
	// constructors
	
	public BookingService(BookingRepository bookingRepository, UserService userService) {
		this.bookingRepository = bookingRepository;
		this.userService = userService;
	}

	// methods
	
	public List<Booking> getBookingsByDate(LocalDate date) {
		List<Booking> result = bookingRepository.findByBookingDate(date);
		
		if (result.isEmpty()) {
			throw new EntityNotFoundException("No booking has been found at this day!");
		}
		
		return result;
	}
	
	public Booking getBookingByUserAndBookingDate(User user, LocalDate date) {
		Optional<Booking> result = bookingRepository.findByUserAndBookingDate(user, date);
		
		Booking booking = null;
		
		if (result.isPresent()) {
			booking = result.get();
		} else {
			throw new EntityNotFoundException("No booking has been found at this day!");
		}
		
		return booking;
	}

	public List<Booking> getBookingsByUser(String email) {
		User user = userService.getUserByMail(email);
		
		List<Booking> result = bookingRepository.findByUser(user);

		return result;
	}

	public String createBooking(Booking booking) {
		boolean bookingExists = bookingRepository.findByUserAndBookingDate(booking.getUser(), booking.getBookingDate())
				.isPresent();
		
		if (bookingExists) {
			throw new IllegalStateException("There has already been made a booking by this user at this day!");
		}
		
		bookingRepository.save(booking);
		
		return "Booking confirmed! Date: " + booking.getBookingDate().format(DateTimeFormatter
			    .ofLocalizedDate(FormatStyle.FULL));
	}

	public String updateBooking(Booking booking) {
		bookingRepository.save(booking);
		
		return "Updated booking: " + booking;
	}

	public String deleteBooking(User user, LocalDate date) {
		bookingRepository.delete(getBookingByUserAndBookingDate(user, date));
		
		return "Booking at " + date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)) + " has been deleted!";
	}

}
