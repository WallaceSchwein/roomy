package dev.willi.roomy.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import dev.willi.roomy.model.entity.Booking;
import dev.willi.roomy.model.entity.Office;
import dev.willi.roomy.repository.OfficeRepository;

@Service
public class OfficeService {
	
	// attributes
	
	private final OfficeRepository officeRepository;
	private final BookingService bookingService;
	
	// constructors
	
	public OfficeService(OfficeRepository officeRepository, BookingService bookingService) {
		this.officeRepository = officeRepository;
		this.bookingService = bookingService;
	}

	// methods
	
	public Office getOfficeByAdress(String adress) {
		Optional<Office> result = officeRepository.findByAdress(adress);
		
		Office office = null;
		
		if (result.isPresent()) {
			office = result.get();
		} else {
			throw new EntityNotFoundException("No office has been found at this adress!");
		}
		
		return office;
	}

	public List<String> getOfficeWorkload(String adress, LocalDate date) {
		Office office = getOfficeByAdress(adress);
		
		List<Booking> listOfBookings = bookingService.getBookingsByDate(date);
		
		int bookings = listOfBookings.size();
		
		List<String> result = new ArrayList<String>(2);
		
		WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
		
		String[] splittedDate = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)).split(" ");
		
		String formattedDate = splittedDate[0] + "/" + weekNumber + "/" + splittedDate[1]; // [Day/week/Month]
		String formattedWorkload = bookings + "/" + office.getCapacity();
		
		result.add(formattedDate);
		result.add(formattedWorkload);

		return result;
	}
	
	public String createOffice(Office office) {
		boolean officeExists = officeRepository.findByAdress(office.getAdress()).isPresent();
		
		if (officeExists) {
			throw new IllegalStateException("An office has already been registered to this adress!");
		}
		
		officeRepository.save(office);
		
		return "New office created! ID: ";
	}

	public String updateOffice(Office office) {
		officeRepository.save(office);

		return "Updated office: " + office;
	}

	public String deleteOfficeByAdress(String adress) {
		officeRepository.delete(getOfficeByAdress(adress));
		
		return "Deleted office registered at adress: " + adress;
	}

}
