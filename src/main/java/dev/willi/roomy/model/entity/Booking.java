package dev.willi.roomy.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {
	
	// attributes
	
	@Id
	@GeneratedValue
	private Long Id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(name = "office_id")
	private Office office;
	private LocalDate bookingDate;
	
	// constructors 
	
	public Booking() {}

	public Booking(User user, Office office, LocalDate bookingDate) {
		this.user = user;
		this.office = office;
		this.bookingDate = bookingDate;
	}

	// getters, setters & toString
	
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	@Override
	public String toString() {
		return "Booking [Id=" + Id + ", user=" + user + ", office=" + office + ", bookingDate=" + bookingDate + "]";
	}
	
}
