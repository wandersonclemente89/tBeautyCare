package br.com.tbeautycare.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SCHEDULE")
public class Schedule {

	@OneToMany
	@JoinColumn(name = "FK_CUST", nullable = false)
	private Customer customer;
	
	@OneToMany
	@JoinColumn(name = "FK_OFFER", nullable = false)
	private Offer offer;

	@Id
	private Date date;
}
