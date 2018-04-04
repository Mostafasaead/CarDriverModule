package com.mytaxi.controller;

/**
 * @author Mostafa El-Gazzar.
 */
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;

@RestController
@RequestMapping("v1/cars")
public class CarController {
	@Autowired
	private CarService carService;

	@RequestMapping(value = "/{carId}", method = RequestMethod.GET)
	public ResponseEntity<CarDTO> getCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
		return new ResponseEntity<>(carService.findCarById(carId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CarDTO>> getAllCars() {
		return new ResponseEntity<>(carService.findAllCars(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carData) throws EntityNotFoundException {
		return new ResponseEntity<>(carService.create(carData), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Void> updateCar(@Valid @RequestBody CarDTO carData) throws EntityNotFoundException {
		carService.update(carData);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{carId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
		carService.delete(carId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
