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
public class DriverCarDTO {
	public DriverCarDTO() {
	}

	public DriverCarDTO(CarDTO carDTO, DriverDTO driverDTO) {
		this.carDTO = carDTO;
		this.driverDTO = driverDTO;
	}

	CarDTO carDTO;
	DriverDTO driverDTO;

}
