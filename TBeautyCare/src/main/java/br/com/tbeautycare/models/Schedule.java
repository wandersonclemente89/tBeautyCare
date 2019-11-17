package br.com.tbeautycare.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@Entity
@Table(name = "SCHEDULE")
@JsonInclude(value = Include.NON_NULL)
public class Schedule {

	@OneToMany(cascade = CascadeType.ALL)
	private Collection<Offer> offers;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SCHED_DATE", unique = true)
	private Date date;

	@Id
	@GeneratedValue
	@Column(name = "SCHED_ID")
	private long id;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Collection<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Collection<Offer> offers) {
		this.offers = offers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
