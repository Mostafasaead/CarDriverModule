package com.mytaxi;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverCarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainvalue.GeoCoordinate;

/**
 * @author Mostafa El-Gazzar.
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class TestData {
	public DriverDTO getDriverDTO() {
		GeoCoordinate geoCoordinate = new GeoCoordinate(90, 90);
		return DriverDTO.newBuilder().setId(1L).setPassword("P@ssw0rd").setUsername("admin")
				.setCoordinate(geoCoordinate).createDriverDTO();
	}

	public DriverCarDTO getDriverCarDTO() {
		return DriverCarDTO.builder().carDTO(getCarDTO()).driverDTO(getDriverDTO()).build();
	}

	public CarDTO getCarDTO() {
		return CarDTO.builder().convertible(true).engineType("gas").licensePlate("abcde12345").seatCount(4)
				.manufacturer("BMW").rating(4.0F).build();
	}

}
