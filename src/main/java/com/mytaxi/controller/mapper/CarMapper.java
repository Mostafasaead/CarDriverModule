package com.mytaxi.controller.mapper;

/**
 * @author Mostafa El-Gazzar.
 */
import java.util.ArrayList;
import java.util.List;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.entity.CarEntity;
import com.mytaxi.entity.ManufacturerEntity;

public class CarMapper {

	public static CarDTO makeCarDTO(CarEntity carEntity) {
		return CarDTO.builder().id(carEntity.getId()).convertible(carEntity.getConvertible())
				.engineType(carEntity.getEngineType()).licensePlate(carEntity.getLicensePlate())
				.manufacturer(carEntity.getManufacturer().getName()).rating(carEntity.getRating())
				.seatCount(carEntity.getSeatCount()).build();
	}

	public static List<CarDTO> makeCarDTOList(Iterable<CarEntity> carEntityList) {
		List<CarDTO> carDTOs = new ArrayList<>();
		carEntityList.forEach(car -> carDTOs.add(makeCarDTO(car)));
		return carDTOs;
	}

	public static CarEntity makeCarEntity(CarDTO carDTO) {
		CarEntity carEntity = new CarEntity();
		carEntity.setConvertible(carDTO.getConvertible());
		carEntity.setEngineType(carDTO.getEngineType());
		carEntity.setLicensePlate(carDTO.getLicensePlate());
		ManufacturerEntity manufacturer = new ManufacturerEntity();
		manufacturer.setName(carDTO.getManufacturer());
		carEntity.setManufacturer(manufacturer);
		carEntity.setRating(carDTO.getRating());
		carEntity.setSeatCount(carDTO.getSeatCount());
		return carEntity;
	}

	public static List<CarEntity> makeCarEntities(Iterable<CarDTO> carEntityList) {
		List<CarEntity> carEntities = new ArrayList<>();
		carEntityList.forEach(car -> carEntities.add(makeCarEntity(car)));
		return carEntities;
	}
}
