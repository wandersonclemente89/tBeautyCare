package br.com.tbeautycare.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "CUSTOMER")
public class Customer {

	@Id 
	@GeneratedValue
	@Column(name = "CUST_ID")
	private long id;
	
	@Email
	@Column(name = "CUST_EMAIL")
	private String email;
	
	@Column(name = "CUST_NAME")
	private String name;
	
	@Column(name = "CUST_PHONENUMBER")
	private String phoneNumber;
	
	@Column(name = "CUST_PASSWORD")
	private String password;
	
//	@OneToOne(cascade = CascadeType.ALL, mappedBy = "id") 
//	@JoinColumn(name = "FK_CONT", nullable = true)
//	private Contact contact;
//
//	public Contact getContact() {
//		return contact;
//	}
//
//	public void setContact(Contact contact) {
//		this.contact = contact;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
