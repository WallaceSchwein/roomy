package dev.willi.roomy.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offices")
public class Office {
	
	// attributes
	
	@Id
	@GeneratedValue //TODO: set start value to 100
	private Long id;
	private String adress;
	private String city;
	private String postCode;
	private Integer capacity;
	
	// constructors
	
	public Office() {}

	public Office(String adress, String city, String postCode, Integer capacity) {
		super();
		this.adress = adress;
		this.city = city;
		this.postCode = postCode;
		this.capacity = capacity;
	}
	
	// getters, setters & toString
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Office [id=" + id + ", adress=" + adress + ", city=" + city + ", postCode=" + postCode + ", capacity="
				+ capacity + "]";
	}
	
}
