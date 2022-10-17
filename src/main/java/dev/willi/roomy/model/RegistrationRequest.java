package dev.willi.roomy.model;

import java.util.Objects;

public class RegistrationRequest {

	//attributes

	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private UserRole role;
	
	//constructors

	public RegistrationRequest(String email, String firstName, String lastName, String password, UserRole role) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.role = role;
	}

	//getters, equals, hashCode & toString

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public String getPassword() {
		return password;
	}
	
	public UserRole getRole() {
		return role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationRequest other = (RegistrationRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& role == other.role;
	}

	@Override
	public String toString() {
		return "RegistrationRequest [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", password=" + password + ", role=" + role + "]";
	}

}
