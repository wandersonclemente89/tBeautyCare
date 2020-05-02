package br.com.tbeautycare.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "CONTACT")
public class Contact {

	@Id
	@GeneratedValue
	@Column(name = "CONT_ID", table = "CONTACT")
	private long id;

	@Column(name = "CONT_ADDRESS", table = "CONTACT")
	private String address;

	@Email
	@Column(name = "CONT_EMAIL", table = "CONTACT")
	private String email;

	@Column(name = "CONT_TELEPHONE_NUMBER", table = "CONTACT")
	private String telephoneNumber;

	@Column(name = "CONT_CITY", table = "CONTACT")
	private String city;

	@Column(name = "CONT_STATE", table = "CONTACT")
	private String state;

	@Column(name = "CONT_COUNTRY", table = "CONTACT")
	private String country;

	@Column(name = "CONT_ZIP_CODE", table = "CONTACT")
	private String zipCode;

	@JsonIgnore
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
