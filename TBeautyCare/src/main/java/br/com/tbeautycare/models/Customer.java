package br.com.tbeautycare.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name = "CUSTOMER")
@JsonInclude(value = Include.NON_NULL)
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name = "CUST_ID")
	private long id;

	@Column(name = "CUST_NAME")
	private String name;

	@Column(name = "CUST_PASSWORD")
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;

	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Schedule> schedules;

	public Collection<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Collection<Schedule> schedules) {
		this.schedules = schedules;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
