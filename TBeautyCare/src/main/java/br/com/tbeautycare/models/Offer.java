package br.com.tbeautycare.models;

import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "OFFER")
@JsonInclude(value = Include.NON_NULL)
public class Offer {

	@Column(name = "OFFER_NAME")
	private String name;

	@Column(name = "OFFER_DESC")
	private String description;

	@Column(name = "OFFER_PRICE")
	private BigDecimal price;

	@Column(name = "OFFER_DURATION_TIME")
	private int durationTime;

	@Id
	@GeneratedValue
	@Column(name = "OFFER_ID")
	private long id;

	@ManyToMany(cascade = CascadeType.ALL)
	private Collection<Schedule> schedules;

	public int getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}

	public Collection<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Collection<Schedule> schedules) {
		this.schedules = schedules;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
