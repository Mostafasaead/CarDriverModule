package com.mytaxi.entity;

/**
 * @author Mostafa El-Gazzar.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
// @Builder
@Table(name = "driver_car")
public class DriverCarEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "driver_id", unique = true)
	private Long driverId;

	@Column(name = "car_id", unique = true)
	private Long carId;
}
