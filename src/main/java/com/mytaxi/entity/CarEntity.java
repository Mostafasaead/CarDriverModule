package com.mytaxi.entity;

/**
 * @author Mostafa El-Gazzar.
 */
import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.mytaxi.domainvalue.OnlineStatus;

import lombok.Data;

@Data
@Entity
@Table(name = "car")
public class CarEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	private Long id;

	@Column(nullable = false, name = "date_created")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(name = "license_plate")
	private String licensePlate;

	@Column(name = "seat_count")
	private Integer seatCount;

	@Column
	private Boolean convertible;
	@Column
	private Float rating;

	@Column(name = "engine_type")
	private String engineType;

	@Column(nullable = false)
	private Boolean deleted = false;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "online_status")
	private OnlineStatus onlineStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "manufacturer")
	private ManufacturerEntity manufacturer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "driver_id", insertable = false, updatable = false)
	private DriverEntity driver;

}
