package com.mytaxi.datatransferobject;

/**
 * @author Mostafa El-Gazzar.
 */
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {
	public CarDTO() {

	}

	public CarDTO(Long id, Float rating, String engingType, int seatCount, boolean convertible, String licensePlate,
			String manufacturer) {
		this.id = id;
		this.rating = rating;
		this.engineType = engingType;
		this.seatCount = seatCount;
		this.convertible = convertible;
		this.licensePlate = licensePlate;
		this.manufacturer = manufacturer;
	}

	private Long id;

	private Float rating;

	private String engineType;

	private Integer seatCount;

	private Boolean convertible;

	private String licensePlate;

	private String manufacturer;

}
