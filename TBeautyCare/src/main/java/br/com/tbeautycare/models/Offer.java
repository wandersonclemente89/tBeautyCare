package br.com.tbeautycare.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "OFFER")
@JsonInclude(value = Include.NON_NULL)
public class Offer {


	@Id
	@GeneratedValue
	@Column(name = "OFFER_ID")
	private long id;

	@Column(name = "OFFER_NAME")
	private String name;

	@Column(name = "OFFER_DESC")
	private String description;

	@Column(name = "OFFER_PRICE")
	private BigDecimal price;

	@Column(name = "OFFER_DURATION_TIME")
	private int durationTime;

	public int getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
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
