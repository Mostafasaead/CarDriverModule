package com.mytaxi.service.car;

/**
 * @author Mostafa El-Gazzar.
 */
import java.util.List;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarService {

	CarDTO findCarById(final Long carId) throws EntityNotFoundException;

	List<CarDTO> findAllCars();

	CarDTO create(final CarDTO car) throws EntityNotFoundException;

	void update(final CarDTO car) throws EntityNotFoundException;

	void delete(final Long carId) throws EntityNotFoundException;
}
